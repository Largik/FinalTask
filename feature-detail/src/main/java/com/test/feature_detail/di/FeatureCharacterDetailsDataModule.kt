package com.test.feature_detail.di

import com.test.core_db.dao.CharactersDao
import com.test.feature_detail.data.CharacterDetailRepositoryImpl
import com.test.feature_detail.domain.repositories.CharacterDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureCharacterDetailsDataModule {
    @Singleton
    @Provides
    fun provideCharacterDetailsRepo(
        charactersDao: CharactersDao
    ): CharacterDetailRepository {
        return CharacterDetailRepositoryImpl(charactersDao)
    }
}