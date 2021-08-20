package app.cookery.repositories.categories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val categoriesStore: CategoriesStore
) {

    suspend fun fetchAllMealsCategories() {
        val response = dataSource.getMealsCategories().getOrThrow()
        categoriesStore.saveMealsCategoryTypes(response)
    }

    suspend fun fetchMealsByCategory(category: String) {
        val response = dataSource.getMealsByCategory(category).getOrThrow()
        categoriesStore.saveMealsByFilter(response)
    }

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        categoriesStore.saveMealsByArea(response)
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        categoriesStore.saveMealsByFilter(response)
    }
}
