package app.cookery.repositories.random

import app.cookery.data.daos.RandomMealDao
import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.RandomMealEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RandomMealStore @Inject constructor(private val dao: RandomMealDao) {

    fun observeRandomMeal(): Flow<MealDetails>? = dao.getRandomMeal()

    suspend fun addRandomMeal(randomMeal: RandomMealEntity) = dao.saveEntityWithLimit(randomMeal)
}
