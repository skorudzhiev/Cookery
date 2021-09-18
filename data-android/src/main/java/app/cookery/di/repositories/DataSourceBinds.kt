package app.cookery.di.repositories

import app.cookery.repositories.categories.CategoriesDataSource
import app.cookery.repositories.categories.TheMealDbCategoryDataSource
import app.cookery.repositories.details.MealDataSource
import app.cookery.repositories.details.TheMealDbMealDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceBinds {

    @Binds
    abstract fun bindCategoriesDataSource(source: TheMealDbCategoryDataSource): CategoriesDataSource

    @Binds
    abstract fun bindMealDataSource(source: TheMealDbMealDataSource): MealDataSource
}
