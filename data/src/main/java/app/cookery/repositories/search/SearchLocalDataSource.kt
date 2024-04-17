package app.cookery.repositories.search

import app.cookery.db.daos.search.LastOpenedMealsDao
import app.cookery.db.daos.search.RecentSearchesDao
import app.cookery.db.entities.search.LastOpenedMealsEntity
import app.cookery.db.entities.search.RecentSearchesEntity
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val recentSearchesDao: RecentSearchesDao,
    private val lastOpenedMealsDao: LastOpenedMealsDao
) {

    fun observeRecentSearches() = recentSearchesDao.getRecentSearches()
    fun observeLastOpenedMeals() = lastOpenedMealsDao.getLastOpenedMeals()

    suspend fun addRecentSearch(recentSearch: String) = recentSearchesDao.insert(
        RecentSearchesEntity(recentSearch)
    )

    suspend fun cleanRecentSearchResults() = recentSearchesDao.clearRecentSearches()

    suspend fun addLastOpenedMeal(mealId: String) = lastOpenedMealsDao.insert(
        LastOpenedMealsEntity(mealId)
    )
}
