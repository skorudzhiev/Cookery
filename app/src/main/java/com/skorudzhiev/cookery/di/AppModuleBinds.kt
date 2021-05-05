package com.skorudzhiev.cookery.di

import app.cookery.appinitializers.AppInitializer
import com.skorudzhiev.cookery.di.appinitializers.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer
}
