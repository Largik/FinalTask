package com.test.feature_character_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.core_db.domain.CharacterModel
import com.test.feature_character_list.domain.usecase.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersListUseCase
) : ViewModel() {

    fun getCharactersFlow(searchString: String): Flow<PagingData<CharacterModel>> =
        getCharactersUseCase(searchString)
            .cachedIn(viewModelScope)
}