package app.cookery.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * from MealDetails WHERE idMeal = :mealId")
    fun getMealDetails(mealId: String): Flow<MealDetails>

    suspend fun insertMealDetails(mealDetailsList: List<MealDetails>) {
        mealDetailsList.forEach {
            insert(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealDetails: MealDetails)
}
