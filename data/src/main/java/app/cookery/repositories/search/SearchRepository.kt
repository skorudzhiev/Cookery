package app.cookery.repositories.search

import app.cookery.data.Result
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.model.MealDetails
import app.cookery.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
    private val localDataSource: SearchLocalDataSource
) : SearchRepository {

    override fun observeRecentSearches(): Flow<List<String>> = localDataSource.observeRecentSearches()
        .map { it.map { recentSearch -> recentSearch.searchQuery } }

    override fun observeLastOpenedMeals(): Flow<List<CategoryDetails>> = localDataSource.observeLastOpenedMeals()

    override suspend fun searchMealByName(mealName: String): Result<List<MealDetails>> =
        remoteDataSource.searchMealByName(mealName).also {
            if (mealName.length >= 4) {
                localDataSource.addRecentSearch(mealName)
            }
        }

    override suspend fun cleanRecentSearchResults() = localDataSource.cleanRecentSearchResults()

    override suspend fun storeLastOpenedMealFromSearchResults(mealId: String) =
        localDataSource.addLastOpenedMeal(mealId)
}
