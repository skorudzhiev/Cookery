package app.cookery.di.repositories

import app.cookery.repositories.categories.remote.CategoriesRemoteDataSource
import app.cookery.repositories.categories.remote.CategoriesRemoteDataSourceImpl
import app.cookery.repositories.details.remote.MealRemoteDataSource
import app.cookery.repositories.details.remote.MealRemoteDataSourceImpl
import app.cookery.repositories.random.RandomRemoteDataSource
import app.cookery.repositories.random.RandomRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataSourceBinds {

    @Binds
    abstract fun bindCategoriesRemoteDataSource(source: CategoriesRemoteDataSourceImpl): CategoriesRemoteDataSource

    @Binds
    abstract fun bindMealRemoteDataSource(source: MealRemoteDataSourceImpl): MealRemoteDataSource

    @Binds
    abstract fun bindRandomRemoteDataSource(source: RandomRemoteDataSourceImpl): RandomRemoteDataSource
}
