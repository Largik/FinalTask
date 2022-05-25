package com.test.feature_detail.di

import com.test.core_db.di.CharacterRepositoryModule
import com.test.core_db.di.DatabaseModule
import com.test.core_network.di.NetworkModule
import com.test.feature_detail.presentation.DetailFragment
import com.test.utils.navigation.di.Feature
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Feature
@Subcomponent(
    modules = [
        NetworkModule:: class,
        DatabaseModule::class,
        CharacterRepositoryModule::class
    ]
)
interface FeatureDetailComponent{
    @Subcomponent.Factory
    interface Factory{
        fun create(): FeatureDetailComponent
    }

    fun inject(fragment: DetailFragment)
//    fun inject(): DetailViewModel
}