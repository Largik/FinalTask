package com.test.feature_character_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.core_db.models.CharacterModel
import com.test.feature_character_list.domain.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CharactersListViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {
    private var _charactersList = MutableLiveData<List<CharacterModel>>()
    val charactersList: LiveData<List<CharacterModel>>
        get() = _charactersList

    init {
        getCharactersList()
    }

    private fun getCharactersList() {
        viewModelScope.launch {
            try {
                _charactersList.value = getCharactersListUseCase.invoke()
            } catch (e: Exception) {
                _charactersList.value = emptyList()
            }
        }
    }
}