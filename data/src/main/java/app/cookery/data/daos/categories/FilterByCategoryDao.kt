package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.FilterMealsByCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterByCategoryDao {

    @Query("SELECT *, `meals` FROM filtered_by_category WHERE category = :category")
    fun getMealsFilteredByCategory(category: String): Flow<FilterMealsByCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meals: FilterMealsByCategory)
}
