package app.cookery.di

import app.cookery.repository.MealsDataSource
import app.cookery.repository.MealsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MealsDataSourceBinds {

    @Binds
    abstract fun bindMealsDataSource(source: MealsDataSource): MealsSource
}
