package com.test.feature_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.test.feature_detail.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    fun getCharacter(characterId: Int) = getCharacterUseCase(characterId).asLiveData()
}