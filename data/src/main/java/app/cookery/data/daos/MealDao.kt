package app.cookery.data.daos

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.CookeryDao
import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao : CookeryDao<MealDetails> {

    @Query("SELECT * from MealDetails WHERE idMeal = :mealId")
    fun getMealDetails(mealId: String): Flow<MealDetails>
}
