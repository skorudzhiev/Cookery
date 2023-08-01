package app.cookery.repositories.details

import app.cookery.data.daos.MealDao
import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealStore @Inject constructor(private val mealDao: MealDao) {

    fun observeMeal(mealId: String): Flow<MealDetails> = mealDao.getMealDetails(mealId)

    fun observeLastMeal(): Flow<MealDetails?> = mealDao.getLastMeal()

    suspend fun saveMeal(mealDetails: List<MealDetails>) = mealDao.insertDetails(mealDetails)
}
