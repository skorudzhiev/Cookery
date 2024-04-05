package app.cookery.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import app.cookery.CookeryRoomDatabase
import app.cookery.RoomTransactionRunner
import app.cookery.db.CookeryDatabase
import app.cookery.db.DatabaseTransactionRunner
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {
    @Provides
    fun provideAreasDao(db: CookeryDatabase) = db.areaDao()

    @Provides
    fun provideCategoriesDao(db: CookeryDatabase) = db.categoriesDao()

    @Provides
    fun provideCategoryDetailsDao(db: CookeryDatabase) = db.categoryDetailsDao()

    @Provides
    fun provideMealDetailsDao(db: CookeryDatabase) = db.mealDetailsDao()

    @Provides
    fun provideFavoritesDao(db: CookeryDatabase) = db.favoritesDao()
}

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
