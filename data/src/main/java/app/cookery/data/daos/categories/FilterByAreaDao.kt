package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.FilterMealsByArea
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterByAreaDao {

    @Query("SELECT *, `meals` FROM filtered_by_area WHERE area = :area")
    fun getMealsFilteredByArea(area: String): Flow<FilterMealsByArea>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meals: FilterMealsByArea)
}
