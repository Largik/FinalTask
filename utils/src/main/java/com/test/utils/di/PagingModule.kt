package com.test.utils.di

import com.test.core_db.RAMDatabase
import com.test.core_network.data.RetrofitApi
import com.test.utils.RemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {
    @Singleton
    @Provides
    fun provideRemoteMediator(
        service: RetrofitApi,
        database: RAMDatabase
    ): RemoteMediator {
        return RemoteMediator(database = database, networkService = service)
    }
}