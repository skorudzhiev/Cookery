package app.cookery.repositories.random

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.MealDetailsEntity
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.meal.MealListToMealDetails
import javax.inject.Inject

interface RandomRemoteDataSource {

    suspend fun getRandomMeal(): Result<List<MealDetailsEntity>>
}

class RandomRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : RandomRemoteDataSource {

    override suspend fun getRandomMeal(): Result<List<MealDetailsEntity>> =
        theMealDbApi.getRandomMeal()
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
}
