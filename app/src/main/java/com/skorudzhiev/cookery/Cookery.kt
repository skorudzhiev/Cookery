package com.skorudzhiev.cookery

import android.app.Application
import com.skorudzhiev.cookery.di.appinitializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Cookery : Application() {
    @Inject lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }
}
