package com.test.feature_character_list.di

import com.test.core_db.RAMDatabase
import com.test.core_network.data.RetrofitApi
import com.test.feature_character_list.data.CharactersListRepositoryImpl
import com.test.feature_character_list.domain.repository.CharactersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterListDataModule {
    @Singleton
    @Provides
    fun provideCharacterListRepo(
        retrofit: RetrofitApi,
        database: RAMDatabase
    ): CharactersListRepository {
        return CharactersListRepositoryImpl(retrofit, database)
    }
}