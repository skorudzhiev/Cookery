package app.cookery.repositories.search

import app.cookery.data.Result
import app.cookery.domain.model.MealDetails
import app.cookery.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
) : SearchRepository {

    override suspend fun searchMealByName(mealName: String): Result<List<MealDetails>> =
        remoteDataSource.searchMealByName(mealName)
}
