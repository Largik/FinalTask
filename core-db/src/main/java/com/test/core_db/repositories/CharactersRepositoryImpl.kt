package com.test.core_db.repositories

import com.test.core_db.dao.CharacterDao
import com.test.core_db.entities.CharacterEntity
import com.test.core_db.models.CharacterModel
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
    ) : CharactersRepository {
    override suspend fun getById(characterId: Int): CharacterModel? {
        return characterDao.getCharacterById(characterId)?.toCharacter()
    }

    override suspend fun getAll(): List<CharacterModel>? {
        return characterDao.getAllCharacters()?.map {
            it.toCharacter()
        }
    }

    override suspend fun saveAll(characters: List<CharacterModel>) {
        characterDao.addCharacters(characters.map { it.toCharacterEntity() })
    }

    private fun CharacterModel.toCharacterEntity(): CharacterEntity = CharacterEntity(
        id = id,
        name = name,
        gender = gender,
        status = status,
        species = species,
        created = created,
        image = image
    )
}
