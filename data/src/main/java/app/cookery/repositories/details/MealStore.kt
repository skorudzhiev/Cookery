package app.cookery.repositories.details

import app.cookery.data.daos.MealDao
import app.cookery.data.entities.MealDetails
import javax.inject.Inject

class MealStore @Inject constructor(
    private val mealDao: MealDao
) {

    suspend fun saveMeal(mealDetails: MealDetails) = mealDao.insertOrUpdate(mealDetails)
}
