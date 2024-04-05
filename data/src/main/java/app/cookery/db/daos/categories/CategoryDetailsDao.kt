package app.cookery.db.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow

private const val CURSOR_MAX_LIMIT = 20

@Dao
interface CategoryDetailsDao : CookeryDao<CategoryDetails> {

    @Query("SELECT * FROM CategoryDetails WHERE area = \"\" ORDER BY RANDOM() LIMIT $CURSOR_MAX_LIMIT")
    fun getRandomCategoryMeals(): Flow<List<CategoryDetails>>

    @Query("SELECT * FROM CategoryDetails WHERE categoryName = \"\" ORDER BY RANDOM() LIMIT $CURSOR_MAX_LIMIT")
    fun getRandomAreaMeals(): Flow<List<CategoryDetails>>

    suspend fun insertCategoryDetails(
        categoryDetailsList: List<CategoryDetails>,
        categoryName: String,
        area: String
    ) {
        categoryDetailsList.forEach {
            it.categoryName = categoryName
            it.area = area
            insert(it)
        }
    }
}
