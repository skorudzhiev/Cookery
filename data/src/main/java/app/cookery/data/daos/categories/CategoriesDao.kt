package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.daos.EntityDao
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.ListedMealsByCategory

@Dao
abstract class CategoriesDao : EntityDao<ListedMealsByCategory>() {

    // TODO: 20.08.21 This will be fixed in the upcoming commit related to storing the retrieved data
//    @Query("SELECT * from meal_categories")
//    abstract fun categoriesObservable(): Flow<ListedMealsByCategory>

    @Query("SELECT * from meal_categories ORDER BY idCategory")
    abstract suspend fun categories(): Category?

    @Query("SELECT * from meal_categories WHERE idCategory = :categoryId ORDER BY idCategory")
    abstract suspend fun categoryWithId(categoryId: Int): Category?
}
