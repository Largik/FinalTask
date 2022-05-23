package com.test.core_network.core_network_impl.di

import com.test.core_network.core_network_api.data.RetrofitClientApi
import com.test.core_network.core_network_impl.data.RetrofitClient
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class NetworkModule {
    @Singleton
    @Binds
    abstract fun provideClient(httpClient: RetrofitClient): RetrofitClientApi
}
