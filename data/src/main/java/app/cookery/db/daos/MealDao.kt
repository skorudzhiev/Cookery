package app.cookery.db.daos

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.MealDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao : CookeryDao<MealDetailsEntity> {

    @Query("SELECT * from MealDetailsEntity WHERE idMeal = :mealId")
    fun getMealDetails(mealId: String): Flow<MealDetailsEntity?>

    @Query("SELECT * FROM MealDetailsEntity ORDER BY insertedAt DESC LIMIT 1")
    fun getLastMeal(): Flow<MealDetailsEntity?>
}
