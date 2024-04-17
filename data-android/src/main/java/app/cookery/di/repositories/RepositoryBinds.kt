package app.cookery.di.repositories

import app.cookery.domain.repositories.AreaRepository
import app.cookery.domain.repositories.CategoriesRepository
import app.cookery.domain.repositories.FavoritesRepository
import app.cookery.domain.repositories.MealRepository
import app.cookery.domain.repositories.RandomRepository
import app.cookery.domain.repositories.SearchRepository
import app.cookery.repositories.areas.AreaRepositoryImpl
import app.cookery.repositories.categories.CategoriesRepositoryImpl
import app.cookery.repositories.details.MealRepositoryImpl
import app.cookery.repositories.favorites.FavoritesRepositoryImpl
import app.cookery.repositories.random.RandomRepositoryImpl
import app.cookery.repositories.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryBinds {

    @Binds
    abstract fun bindRandomRepository(repository: RandomRepositoryImpl): RandomRepository

    @Binds
    abstract fun bindMealRepository(repository: MealRepositoryImpl): MealRepository

    @Binds
    abstract fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    abstract fun bindCategoriesRepository(repository: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun bindAreaRepository(repository: AreaRepositoryImpl): AreaRepository

    @Binds
    abstract fun bindSearchRepository(repository: SearchRepositoryImpl): SearchRepository
}
