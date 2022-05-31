package com.test.core_db.di

import android.app.Application
import com.test.core_db.RAMDatabase
import com.test.core_db.dao.CharactersDao
import com.test.core_db.dao.RemoteKeyDao
import com.test.core_db.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseInstance(application: Application): RAMDatabase {
        return getDatabase(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: RAMDatabase): CharactersDao {
        return database.charactersDao()
    }

    @Singleton
    @Provides
    fun provideRemoteKeysDao(database: RAMDatabase): RemoteKeyDao {
        return database.remoteKeyDao()
    }
}