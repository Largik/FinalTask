package com.test.feature_character_list.domain.repository

import androidx.paging.PagingData
import com.test.core_db.domain.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersListRepository {
    fun getCharacters(searchString: String): Flow<PagingData<CharacterModel>>
}