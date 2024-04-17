package app.cookery.domain.repositories

import app.cookery.data.Result
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.model.MealDetails
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun observeRecentSearches(): Flow<List<String>>
    fun observeLastOpenedMeals(): Flow<List<CategoryDetails>>

    suspend fun searchMealByName(mealName: String): Result<List<MealDetails>>

    suspend fun cleanRecentSearchResults()

    suspend fun storeLastOpenedMealFromSearchResults(mealId: String)
}
