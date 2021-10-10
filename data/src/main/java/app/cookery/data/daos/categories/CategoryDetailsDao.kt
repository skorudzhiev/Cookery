package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDetailsDao {

    @Query("SELECT * FROM CategoryDetails WHERE categoryName = :categoryName")
    fun getCategoryDetailsByName(categoryName: String): Flow<CategoryDetails>

    @Query("SELECT * FROM CategoryDetails WHERE area = :area")
    fun getCategoryDetailsByArea(area: String): Flow<CategoryDetails>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryDetails: CategoryDetails)
}
