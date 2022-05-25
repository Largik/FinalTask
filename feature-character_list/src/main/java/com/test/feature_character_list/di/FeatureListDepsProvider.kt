package com.test.feature_character_list.di

import kotlin.properties.Delegates.notNull

interface FeatureListDepsProvider {
    val deps: FeatureListDeps

    companion object: FeatureListDepsProvider by FeatureListDepsStore
}

object FeatureListDepsStore: FeatureListDepsProvider{
    override var deps: FeatureListDeps by notNull()
}