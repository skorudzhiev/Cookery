package app.cookery.repositories.details.remote

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.MealDetailsEntity
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.meal.MealListToMealDetails
import javax.inject.Inject

class MealRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : MealRemoteDataSource {

    override suspend fun getMealDetails(mealId: String): Result<List<MealDetailsEntity>> {
        return theMealDbApi.getMealDetails(mealId)
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
    }
}
