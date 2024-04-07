package app.cookery.repositories.random

import app.cookery.domain.model.MealDetails
import app.cookery.domain.repositories.RandomRepository
import app.cookery.mappers.meal.MealDetailsMapper
import app.cookery.repositories.details.MealLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RandomRepositoryImpl @Inject constructor(
    private val randomRemoteDataSource: RandomRemoteDataSource,
    private val mealLocalDataSource: MealLocalDataSource,
    private val mealDetailsMapper: MealDetailsMapper
) : RandomRepository {

    override fun observeLastMeal(): Flow<MealDetails?> = mealLocalDataSource.observeLastMeal()
        .map { meal -> meal?.let { mealDetailsMapper.map(it) } }

    override suspend fun getRandomMeal() {
        val response = randomRemoteDataSource.getRandomMeal().getOrThrow()
        mealLocalDataSource.saveMeal(mealDetailEntities = response)
    }
}
