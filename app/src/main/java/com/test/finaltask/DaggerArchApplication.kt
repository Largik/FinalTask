package com.test.finaltask

import android.app.Application
import com.test.feature_character_list.di.FeatureListDepsStore
import com.test.finaltask.di.AppComponent
import com.test.finaltask.di.DaggerAppComponent

class DaggerArchApplication : Application() {
   val appComponent: AppComponent by lazy {
      DaggerAppComponent.builder()
         .application(this)
         .apiKey(BuildConfig.API_KEY)
         .build()
   }

   override fun onCreate() {
      super.onCreate()
      FeatureListDepsStore.deps = appComponent
   }
}