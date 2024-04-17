package app.cookery.db.daos.search

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.search.LastOpenedMealsEntity
import app.cookery.domain.model.CategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface LastOpenedMealsDao : CookeryDao<LastOpenedMealsEntity> {

    @Query(
        """
        SELECT MealDetailsEntity.idMeal, MealDetailsEntity.mealName, MealDetailsEntity.mealImage
        FROM LastOpenedMealsEntity
        INNER JOIN MealDetailsEntity
        ON LastOpenedMealsEntity.idMeal = MealDetailsEntity.idMeal
    """
    )
    fun getLastOpenedMeals(): Flow<List<CategoryDetails>>
}
