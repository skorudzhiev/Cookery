package app.cookery.repositories.random

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.MealDetails
import app.cookery.data.mappers.MealListToMealDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

interface RandomDataSource {

    suspend fun getRandomMeal(): Result<List<MealDetails>>
}

class RandomDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : RandomDataSource {

    override suspend fun getRandomMeal(): Result<List<MealDetails>> =
        theMealDbApi.getRandomMeal()
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
}
