package com.test.feature_detail.data

import com.test.core_db.dao.CharactersDao
import com.test.feature_detail.domain.repositories.CharacterDetailRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao) :
    CharacterDetailRepository {
    override fun getCharacter(characterId: Int) =
        charactersDao.getCharacter(characterId).map { characterEntity ->
            characterEntity.toCharacter()
        }
}