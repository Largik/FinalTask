package com.test.feature_character_list.domain.usecase

import androidx.paging.PagingData
import com.test.core_db.domain.CharacterModel
import com.test.feature_character_list.domain.repository.CharactersListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repo: CharactersListRepository
) {
    operator fun invoke(searchString: String): Flow<PagingData<CharacterModel>> {
        return repo.getCharacters(searchString)
    }
}
