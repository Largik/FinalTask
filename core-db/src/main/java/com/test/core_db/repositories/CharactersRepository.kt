package com.test.core_db.repositories

import com.test.core_db.models.CharacterModel

interface CharactersRepository {
    suspend fun getById(characterId: Int): CharacterModel?
    suspend fun getAll(): List<CharacterModel>?
    suspend fun saveAll(characters: List<CharacterModel>)
}