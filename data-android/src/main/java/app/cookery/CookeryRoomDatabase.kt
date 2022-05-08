package app.cookery

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cookery.data.entities.Favorites
import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails

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
