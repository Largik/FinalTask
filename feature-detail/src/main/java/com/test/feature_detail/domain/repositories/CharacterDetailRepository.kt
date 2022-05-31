package com.test.feature_detail.domain.repositories

import com.test.core_db.domain.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    fun getCharacter(characterId: Int): Flow<CharacterModel>
}