package app.cookery.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import app.cookery.data.CookeryDao
import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.RandomMealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomMealDao : CookeryDao<RandomMealEntity> {

    @Query(
        """
        SELECT idMeal FROM MealDetails
        WHERE idMeal NOT IN (SELECT idMeal FROM Favorites)
        AND idMeal NOT IN (SELECT idMeal FROM RandomMealEntity)
        ORDER BY RANDOM()
        LIMIT 1
        """
    )
    fun getRandomOfflineMeal(): Flow<MealDetails?>

    @Query(
        """
        SELECT idMeal FROM MealDetails
        WHERE (SELECT COUNT(*) FROM MealDetails) >= 30
          AND idMeal NOT IN (SELECT idMeal FROM Favorites)
          AND idMeal NOT IN (SELECT idMeal FROM RandomMealEntity)
        ORDER BY RANDOM()
        LIMIT 1
        """
    )
    fun getRandomMeal(): Flow<MealDetails?>

    @Query("SELECT COUNT(*) FROM RandomMealEntity")
    fun countEntities(): Int

    @Query("DELETE FROM RandomMealEntity")
    fun dropRandomMealTable()

    @Transaction
    suspend fun saveEntityWithLimit(entity: RandomMealEntity) {
        if (countEntities() < 5) {
            insert(entity)
        } else {
            dropRandomMealTable()
            insert(entity)
        }
    }
}
