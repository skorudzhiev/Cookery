package app.cookery.di

import app.cookery.TheMealDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TheMealDbModule {

    @Singleton
    @Provides
    fun provideTheMealDbApiService(
        retrofit: Retrofit
    ): TheMealDbApi {
        return retrofit.create(TheMealDbApi::class.java)
    }
}
