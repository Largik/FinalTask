package com.test.feature_character_list.di

import com.test.feature_character_list.domain.repository.CharactersListRepository
import com.test.feature_character_list.domain.usecase.GetCharactersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class CharactersListModule {
    @Provides
    fun provideUseCase(repo: CharactersListRepository): GetCharactersListUseCase {
        return GetCharactersListUseCase(repo)
    }
}

