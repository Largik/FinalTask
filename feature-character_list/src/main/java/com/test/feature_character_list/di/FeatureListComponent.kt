package com.test.feature_character_list.di

import com.test.core_db.di.CharacterRepositoryModule
import com.test.core_db.di.DatabaseModule
import com.test.core_network.data.RetrofitService
import com.test.core_network.di.NetworkModule
import com.test.feature_character_list.presentation.CharactersListFragment
import com.test.utils.navigation.di.Feature
import dagger.Component

@[Feature Component(dependencies = [FeatureListDeps::class])]
internal interface FeatureListComponent {

    fun inject(fragment: CharactersListFragment)

    @Component.Builder
    interface Builder {

        fun deps(deps: FeatureListDeps):Builder

        fun build(): FeatureListComponent
    }
}

