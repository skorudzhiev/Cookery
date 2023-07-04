package app.cookery.repositories.random

import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.RandomMealEntity
import app.cookery.repositories.details.MealStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RandomRepository {

    fun observeRandomMeal(shouldUseOfflinePolicy: Boolean = false): Flow<MealDetails?>

    suspend fun getRandomMeal()
}

class RandomRepositoryImpl @Inject constructor(
    private val randomDataSource: RandomDataSource,
    private val randomMealStore: RandomMealStore,
    private val mealStore: MealStore
) : RandomRepository {

    override fun observeRandomMeal(shouldUseOfflinePolicy: Boolean) = if (shouldUseOfflinePolicy) {
        randomMealStore.observeRandomOfflineMeal()
    } else {
        randomMealStore.observeRandomMeal()
    }

    override suspend fun getRandomMeal() {
        val response = randomDataSource.getRandomMeal().getOrThrow()
        storeResponse(response)
    }

    private suspend fun storeResponse(response: List<MealDetails>) {
        mealStore.saveMeal(mealDetails = response)
        randomMealStore.addRandomMeal(randomMeal = RandomMealEntity(mealId = response[0].mealId))
    }
}
