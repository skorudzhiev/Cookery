package app.cookery

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cookery.db.CookeryDatabase
import app.cookery.db.entities.Favorites
import app.cookery.db.entities.MealDetails
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails

@Database(
    entities = [
        Category::class,
        Area::class,
        CategoryDetails::class,
        MealDetails::class,
        Favorites::class
    ],
    version = 1
)
abstract class CookeryRoomDatabase : RoomDatabase(), CookeryDatabase
