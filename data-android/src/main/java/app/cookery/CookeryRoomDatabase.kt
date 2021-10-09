package app.cookery

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.CategoriesTypeConverter
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.CategoryDetailsTypeConverter
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory

@Database(
    entities = [
        Category::class,
        AllMealCategories::class,
        Area::class,
        CategoryDetails::class,
        FilterMealsByArea::class,
        FilterMealsByCategory::class,
        MealDetails::class
    ],
    version = 1
)
@TypeConverters(
    CategoriesTypeConverter::class,
    CategoryDetailsTypeConverter::class
)
abstract class CookeryRoomDatabase : RoomDatabase(), CookeryDatabase
