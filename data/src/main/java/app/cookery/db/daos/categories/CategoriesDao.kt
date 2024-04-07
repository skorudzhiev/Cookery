package app.cookery.db.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.domain.model.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao : CookeryDao<CategoryEntity> {

    @Query("SELECT * FROM CategoryEntity")
    fun getAllMealCategories(): Flow<List<CategoryEntity>>

    @Query(
        """
            SELECT CategoryDetailsEntity.mealId, mealName, mealImage,
                   CategoryEntity.categoryName, categoryImage, categoryDescription
            FROM CategoryEntity
            INNER JOIN CategoryDetailsEntity
            ON CategoryEntity.categoryName = CategoryDetailsEntity.categoryName
            WHERE CategoryEntity.categoryName = :categoryName
        """
    )
    fun getCategoryWithCategoryDetails(categoryName: String): Flow<List<CategoryWithCategoryDetails>>
}
