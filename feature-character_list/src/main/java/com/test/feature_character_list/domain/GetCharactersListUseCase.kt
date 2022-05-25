package com.test.feature_character_list.domain

import com.test.core_db.models.CharacterModel
import com.test.core_db.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(private val repo: CharactersRepository){
    suspend operator fun invoke(): List<CharacterModel>?{
        return repo.getAll()
    }
}