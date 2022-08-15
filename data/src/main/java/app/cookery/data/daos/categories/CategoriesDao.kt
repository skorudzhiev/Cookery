package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.CookeryDao
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.relations.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao : CookeryDao<Category> {

    @Query("SELECT * FROM Category")
    fun getAllMealCategories(): Flow<List<Category>>

    @Query(
        """
            SELECT CategoryDetails.mealId, mealName, mealImage,
                   Category.categoryName, categoryImage, categoryDescription
            FROM Category
            INNER JOIN CategoryDetails
            ON Category.categoryName = CategoryDetails.categoryName
            WHERE Category.categoryName = :categoryName
        """
    )
    fun getCategoryWithCategoryDetails(categoryName: String): Flow<List<CategoryWithCategoryDetails>>
}
