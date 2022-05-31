package com.test.feature_character_list.data

import androidx.paging.*
import com.test.core_db.RAMDatabase
import com.test.core_db.domain.CharacterModel
import com.test.core_network.data.RetrofitApi
import com.test.feature_character_list.domain.repository.CharactersListRepository
import com.test.utils.RemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersListRepositoryImpl @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val db: RAMDatabase
) : CharactersListRepository {
    override fun getCharacters(searchString: String): Flow<PagingData<CharacterModel>> {
        val pagingSourceFactory = { db.charactersDao().getCharacters(searchString) }
        val remoteMediator = RemoteMediator(db, retrofitApi)
        remoteMediator.selectQuery(searchString)

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pData ->
            pData.map { characterEntity ->
                characterEntity.toCharacter()
            }
        }
    }

    private companion object {
        const val PAGE_SIZE = 15
    }
}