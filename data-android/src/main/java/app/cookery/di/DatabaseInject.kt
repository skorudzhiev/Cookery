package app.cookery.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import app.cookery.CookeryDatabase
import app.cookery.DatabaseTransactionRunner
import app.cookery.data.CookeryRoomDatabase
import app.cookery.data.RoomTransactionRunner
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModuleBinds {
    @Binds
    abstract fun bindCookeryDatabase(db: CookeryRoomDatabase): CookeryDatabase

    @Singleton
    @Binds
    abstract fun provideDatabaseTransactionRunner(runner: RoomTransactionRunner): DatabaseTransactionRunner
}

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CookeryRoomDatabase {
        val builder = Room.databaseBuilder(context, CookeryRoomDatabase::class.java, "recipes.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}
