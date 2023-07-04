package app.cookery.repositories.random

import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.RandomMealEntity
import app.cookery.repositories.details.MealStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.transform
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

    override fun observeRandomMeal(shouldUseOfflinePolicy: Boolean): Flow<MealDetails?> {
        val result = if (shouldUseOfflinePolicy) {
            randomMealStore.observeRandomOfflineMeal().storeResponse()
        } else {
            randomMealStore.observeRandomMeal().storeResponse()
        }
        return result
    }

    override suspend fun getRandomMeal() {
        val response = randomDataSource.getRandomMeal().getOrThrow()
        mealStore.saveMeal(mealDetails = response)
    }

    private fun Flow<MealDetails?>.storeResponse() =
        filterNotNull().transform { mealDetails ->
            randomMealStore.addRandomMeal(randomMeal = RandomMealEntity(mealId = mealDetails.mealId))
            emit(mealDetails)
        }
}
