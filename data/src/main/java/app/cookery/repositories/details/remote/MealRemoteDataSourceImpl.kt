package app.cookery.repositories.details.remote

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.MealDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.MealListToMealDetails
import javax.inject.Inject

class MealRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : MealRemoteDataSource {

    override suspend fun getMealDetails(mealId: String): Result<List<MealDetails>> {
        return theMealDbApi.getMealDetails(mealId)
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
    }
}
