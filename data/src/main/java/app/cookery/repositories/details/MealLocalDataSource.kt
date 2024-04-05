package app.cookery.repositories.details

import app.cookery.db.daos.MealDao
import app.cookery.db.entities.MealDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealLocalDataSource @Inject constructor(private val mealDao: MealDao) {

    fun observeMeal(mealId: String): Flow<MealDetails> = mealDao.getMealDetails(mealId)

    fun observeLastMeal(): Flow<MealDetails?> = mealDao.getLastMeal()

    suspend fun saveMeal(mealDetails: List<MealDetails>) = mealDao.insertDetails(mealDetails)
}
