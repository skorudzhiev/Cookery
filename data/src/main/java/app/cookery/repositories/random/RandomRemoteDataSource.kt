package app.cookery.repositories.random

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.MealDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.MealListToMealDetails
import javax.inject.Inject

interface RandomRemoteDataSource {

    suspend fun getRandomMeal(): Result<List<MealDetails>>
}

class RandomRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : RandomRemoteDataSource {

    override suspend fun getRandomMeal(): Result<List<MealDetails>> =
        theMealDbApi.getRandomMeal()
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
}
