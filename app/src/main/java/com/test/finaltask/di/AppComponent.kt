package com.test.finaltask.di

import android.app.Application
import android.content.Context
import com.test.core_network.data.RetrofitService
import com.test.feature_character_list.di.FeatureListDeps
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(modules = [AppModule::class])]
abstract class AppComponent: FeatureListDeps {

    override val retrofitService: RetrofitService

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiKey(@FeatureListApiQualifier apiKey: String): Builder

        fun build(): AppComponent
    }}