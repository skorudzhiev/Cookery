package app.cookery.repositories.details

import app.cookery.db.daos.MealDao
import app.cookery.db.entities.MealDetailsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealLocalDataSource @Inject constructor(private val mealDao: MealDao) {

    fun observeMeal(mealId: String): Flow<MealDetailsEntity?> = mealDao.getMealDetails(mealId)

    fun observeLastMeal(): Flow<MealDetailsEntity?> = mealDao.getLastMeal()

    suspend fun saveMeal(mealDetailEntities: List<MealDetailsEntity>) = mealDao.insertDetails(mealDetailEntities)
}
