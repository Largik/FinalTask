package com.test.finaltask.di

import android.content.Context
import com.test.finaltask.DaggerArchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return DaggerArchApplication.appContext
    }


//    @Singleton
//    @Provides
//    fun provideCharacterListFeatureDependencies(): CharacterListFeatureDependencies {
//
//        return object : CharacterListFeatureDependencies {
//              //TODO
//        }
//    }
}