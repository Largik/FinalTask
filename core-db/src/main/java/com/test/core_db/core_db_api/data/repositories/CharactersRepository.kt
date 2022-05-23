package com.test.core_db.core_db_api.data.repositories

import com.test.core_db.core_db_api.data.models.CharacterModel

interface CharactersRepository {
    suspend fun getById(characterId: Int): CharacterModel?
    suspend fun getAll(): List<CharacterModel>?
    suspend fun saveAll(characters: List<CharacterModel>)
}
