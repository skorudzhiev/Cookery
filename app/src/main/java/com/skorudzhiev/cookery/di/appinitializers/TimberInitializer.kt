package com.skorudzhiev.cookery.di.appinitializers

import android.app.Application
import app.cookery.appinitializers.AppInitializer
import app.cookery.appinitializers.CookeryLogger
import com.skorudzhiev.cookery.app.BuildConfig
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val cookeryLogger: CookeryLogger
) : AppInitializer {
    override fun init(application: Application) = cookeryLogger.setup(BuildConfig.DEBUG)
}
