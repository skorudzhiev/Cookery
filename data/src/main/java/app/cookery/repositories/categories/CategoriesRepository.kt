package app.cookery.repositories.categories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource
) {

    suspend fun fetchAllMealsCategories() {
        val response = dataSource.getMealsCategories().getOrThrow()
        // TODO: 20.08.21 Save data in the DB
    }

    suspend fun fetchMealsByCategory(category: String) {
        val response = dataSource.getMealsByCategory(category).getOrThrow()
        // TODO: 20.08.21 Save data in the DB
    }

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        // TODO: 20.08.21 Save data in the DB
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        // TODO: 20.08.21 Save data in the DB
    }
}
