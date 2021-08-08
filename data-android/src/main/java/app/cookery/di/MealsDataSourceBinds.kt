package app.cookery.di

import app.cookery.repositories.categories.CategoriesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MealsDataSourceBinds {

    @Binds
    abstract fun bindMealsDataSource(source: CategoriesDataSource): CategoriesDataSource
}
