package app.cookery.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.cookery.CookeryDatabase
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.MealsByArea

@Database(
    entities = [
        Category::class,
        CategoryDetails::class,
        MealsByArea::class
    ],
    version = 1
)
abstract class CookeryRoomDatabase : RoomDatabase(), CookeryDatabase
