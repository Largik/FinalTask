package com.test.finaltask.di

import com.test.core_network.data.RetrofitService
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @[Provides AppScope]
    fun provideFeatureListService(@FeatureListApiQualifier apiKey: String) = RetrofitService(apiKey)
}