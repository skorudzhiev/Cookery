package com.skorudzhiev.cookery.di

import app.cookery.Logger
import app.cookery.appinitializers.AppInitializer
import app.cookery.appinitializers.CookeryLogger
import com.skorudzhiev.cookery.di.appinitializers.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun provideLogger(bind: CookeryLogger): Logger

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer
}
