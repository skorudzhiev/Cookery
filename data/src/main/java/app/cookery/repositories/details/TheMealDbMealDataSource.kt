package app.cookery.repositories.details

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.MealDetails
import app.cookery.data.mappers.MealListToMealDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

class TheMealDbMealDataSource @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealListToMealDetails
) : MealDataSource {
    override suspend fun getMealDetails(mealId: String): Result<List<MealDetails>> {
        return theMealDbApi.getMealDetails(mealId)
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
    }
}
