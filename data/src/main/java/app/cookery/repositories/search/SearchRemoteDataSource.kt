package app.cookery.repositories.search

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.domain.model.MealDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.meal.MealDetailsDtoToDomainMapper
import javax.inject.Inject

interface SearchRemoteDataSource {

    suspend fun searchMealByName(mealName: String): Result<List<MealDetails>>
}

class SearchRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val mealDetailsMapper: MealDetailsDtoToDomainMapper
) : SearchRemoteDataSource {

    override suspend fun searchMealByName(mealName: String): Result<List<MealDetails>> =
        theMealDbApi.searchMealByName(mealName)
            .executeWithRetry()
            .toResult(mealDetailsMapper::map)
}
