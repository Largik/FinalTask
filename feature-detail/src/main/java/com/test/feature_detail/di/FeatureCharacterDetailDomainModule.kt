package com.test.feature_detail.di

import com.test.feature_detail.domain.repositories.CharacterDetailRepository
import com.test.feature_detail.domain.usecase.GetCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class FeatureCharacterDetailDomainModule {
    @Provides
    fun provideUseCase(repo: CharacterDetailRepository): GetCharacterUseCase {
        return GetCharacterUseCase(repo)
    }
}