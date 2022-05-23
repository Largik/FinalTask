package com.test.finaltask.di

import com.test.finaltask.DaggerArchApplication
import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
abstract class AppComponent {
    abstract fun inject(daggerArchApplication: DaggerArchApplication)

    companion object {
        @Volatile
        private var instance: AppComponent? = null

        fun get(): AppComponent {
            return Preconditions.checkNotNull(instance,
                    "AppComponent is not initialized yet. Call init first.")!!
        }

        fun init(component: AppComponent) {
            require(instance == null) { "AppComponent is already initialized." }
            instance = component
        }
    }
}