package app.cookery.repositories.details

import app.cookery.data.daos.MealDao
import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealStore @Inject constructor(
    private val mealDao: MealDao
) {

    fun observeMeal(mealId: String): Flow<MealDetails> {
        return mealDao.getMealDetails(mealId)
    }

    suspend fun saveMeal(mealDetails: List<MealDetails>) = mealDao.insertMealDetails(mealDetails)
}
