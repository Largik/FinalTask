package com.test.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.test.core_db.RAMDatabase
import com.test.core_db.entities.CharacterEntity
import com.test.core_db.entities.RemoteKey
import com.test.core_network.data.RetrofitApi
import com.test.core_network.data.toCharacterEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RemoteMediator @Inject constructor(
    private val database: RAMDatabase,
    private val networkService: RetrofitApi
) : RemoteMediator<Int, CharacterEntity>() {

    private var query = QUERY

    fun selectQuery(searchQuery: String) {
        query = searchQuery
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: START_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                nextKey
            }
        }

        try {
            val apiResponse = networkService.getCharacters(loadKey, query)

            val characters = apiResponse.results
            val endOfPaginationReached = apiResponse.info.next == null

            database.withTransaction {
                val prevKey = if (apiResponse.info.prev == null) null else loadKey - 1
                val nextKey = if (endOfPaginationReached) null else loadKey + 1
                val key = characters.map {
                    RemoteKey(
                        id = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                database.remoteKeyDao().insertOrReplace(remoteKey = key)
                database.charactersDao()
                    .insertOrReplace(characters = characters.toCharacterEntity())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            if (exception.code() == ERROR_404 && query.isNotEmpty()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            return MediatorResult.Error(exception)
        }

    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        return state.pages.lastOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { character ->
            database.remoteKeyDao().remoteKeyById(character.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKey? {
        return state.pages.firstOrNull { page ->
            page.data.isNotEmpty()
        }?.data?.firstOrNull()
            ?.let { character ->
                database.remoteKeyDao().remoteKeyById(character.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId ->
                database.remoteKeyDao().remoteKeyById(characterId)
            }
        }
    }

    private companion object {
        const val QUERY = ""
        const val START_PAGE = 1
        const val ERROR_404 = 404
    }
}
