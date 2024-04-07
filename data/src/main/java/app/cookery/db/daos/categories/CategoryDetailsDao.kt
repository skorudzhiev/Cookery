package app.cookery.db.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.categories.CategoryDetailsEntity
import kotlinx.coroutines.flow.Flow

private const val CURSOR_MAX_LIMIT = 20

@Dao
interface CategoryDetailsDao : CookeryDao<CategoryDetailsEntity> {

    @Query("SELECT * FROM CategoryDetailsEntity WHERE area = \"\" ORDER BY RANDOM() LIMIT $CURSOR_MAX_LIMIT")
    fun getRandomCategoryMeals(): Flow<List<CategoryDetailsEntity>>

    @Query("SELECT * FROM CategoryDetailsEntity WHERE categoryName = \"\" ORDER BY RANDOM() LIMIT $CURSOR_MAX_LIMIT")
    fun getRandomAreaMeals(): Flow<List<CategoryDetailsEntity>>

    suspend fun insertCategoryDetails(
        categoryDetailsEntityList: List<CategoryDetailsEntity>,
        categoryName: String,
        area: String
    ) {
        categoryDetailsEntityList.forEach {
            it.categoryName = categoryName
            it.area = area
            insert(it)
        }
    }
}
