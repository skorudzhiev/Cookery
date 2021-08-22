package app.cookery.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * from meal_details WHERE idMeal = :mealId")
    fun getMealDetails(mealId: String): Flow<MealDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealDetails: MealDetails)
}
