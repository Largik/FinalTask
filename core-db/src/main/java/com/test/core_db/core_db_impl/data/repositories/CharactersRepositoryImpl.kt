package com.test.core_db.core_db_impl.data.repositories

import com.test.core_db.core_db_api.data.models.CharacterModel
import com.test.core_db.core_db_api.data.repositories.CharactersRepository
import com.test.core_db.core_db_impl.data.dao.CharacterDao
import com.test.core_db.core_db_impl.data.entities.CharacterEntity

class CharactersRepositoryImpl(
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
        characterDao.saveCharacters(characters.map { it.toCharacterEntity() })
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
