package com.test.core_db.di

import android.app.Application
import com.test.core_db.AppDatabase
import com.test.core_db.dao.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomInstance(application: Application): AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.getCharacterDao()
    }
}