package com.test.finaltask

import android.app.Application
import android.content.Context
import com.test.finaltask.di.AppComponent
import com.test.finaltask.di.DaggerAppComponent

class DaggerArchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        AppComponent.init(
            DaggerAppComponent.builder()
                .build()
        )
        AppComponent.get().inject(this)
//        CharactersListComponentHolder.init(object : CharactersListFeatureDependencies {
//            //TODO
//        })

    }

    companion object {
        @Volatile
        lateinit var appContext: Context
            private set
    }
}