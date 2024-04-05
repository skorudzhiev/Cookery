package app.cookery.repositories.random

import app.cookery.db.entities.MealDetails
import app.cookery.repositories.details.MealLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RandomRepository {

    fun observeLastMeal(): Flow<MealDetails?>

    suspend fun getRandomMeal()
}

class RandomRepositoryImpl @Inject constructor(
    private val randomRemoteDataSource: RandomRemoteDataSource,
    private val mealLocalDataSource: MealLocalDataSource
) : RandomRepository {

    override fun observeLastMeal(): Flow<MealDetails?> = mealLocalDataSource.observeLastMeal()

    override suspend fun getRandomMeal() {
        val response = randomRemoteDataSource.getRandomMeal().getOrThrow()
        mealLocalDataSource.saveMeal(mealDetails = response)
    }
}
