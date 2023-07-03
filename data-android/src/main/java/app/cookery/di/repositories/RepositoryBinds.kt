package app.cookery.di.repositories

import app.cookery.repositories.random.RandomRepository
import app.cookery.repositories.random.RandomRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryBinds {

    @Binds
    abstract fun bindRandomRepository(repository: RandomRepositoryImpl): RandomRepository
}
