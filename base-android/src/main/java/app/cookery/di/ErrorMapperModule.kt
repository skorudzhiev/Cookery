package app.cookery.di

import android.content.Context
import app.cookery.ErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ErrorMapperModule {

    @Provides
    fun provideErrorMapper(@ApplicationContext context: Context) = ErrorMapper(context)
}
