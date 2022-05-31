package com.test.feature_detail.domain.usecase

import com.test.core_db.domain.CharacterModel
import com.test.feature_detail.domain.repositories.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repo: CharacterDetailRepository
) {
    operator fun invoke(characterId: Int): Flow<CharacterModel> {
        return repo.getCharacter(characterId)
    }
}