package com.test.core_db.di

import com.test.core_db.repositories.CharactersRepository
import com.test.core_db.repositories.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CharacterRepositoryModule {
    @Binds
    abstract fun provideCharacterRepository(repo: CharactersRepositoryImpl): CharactersRepository
}