package app.cookery

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cookery.db.CookeryDatabase
import app.cookery.db.entities.FavoritesEntity
import app.cookery.db.entities.MealDetailsEntity
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.db.entities.search.LastOpenedMealsEntity
import app.cookery.db.entities.search.RecentSearchesEntity

@Database(
    entities = [
        CategoryEntity::class,
        AreaEntity::class,
        CategoryDetailsEntity::class,
        MealDetailsEntity::class,
        FavoritesEntity::class,
        RecentSearchesEntity::class,
        LastOpenedMealsEntity::class
    ],
    version = 1
)
abstract class CookeryRoomDatabase : RoomDatabase(), CookeryDatabase
