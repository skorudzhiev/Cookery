package app.cookery.repositories.random

import app.cookery.data.entities.MealDetails
import app.cookery.repositories.details.MealStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RandomRepository {

    fun observeLastMeal(): Flow<MealDetails?>

    suspend fun getRandomMeal()
}

class RandomRepositoryImpl @Inject constructor(
    private val randomDataSource: RandomDataSource,
    private val mealStore: MealStore
) : RandomRepository {

    override fun observeLastMeal(): Flow<MealDetails?> = mealStore.observeLastMeal()

    override suspend fun getRandomMeal() {
        val response = randomDataSource.getRandomMeal().getOrThrow()
        mealStore.saveMeal(mealDetails = response)
    }
}
