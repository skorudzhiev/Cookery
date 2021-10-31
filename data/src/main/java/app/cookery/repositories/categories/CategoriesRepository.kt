package app.cookery.repositories.categories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val store: CategoriesStore
) {

    fun observeCategoryDetailsByName(categoryName: String) = store.observeCategoryDetailsByName(categoryName)
    fun observeCategoryDetailsByArea(area: String) = store.observeCategoryDetailsByArea(area)
    fun observeRandomCategoryDetails() = store.observeRandomCategoryMeals()
    fun observeRandomAreaMeals() = store.observeRandomAreaMeals()
    fun observeAllMealCategories() = store.observeAllMealCategories()
    fun observeAreaMeals() = store.observeAreaMeals()

    suspend fun fetchAllMealCategories() {
        val response = dataSource.getAllMealCategories().getOrThrow()
        store.saveAllMealCategories(response)
    }

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        store.saveAreaMeals(response)
    }

    suspend fun fetchMealsByCategory(categoryName: String) {
        val response = dataSource.getMealsByCategory(categoryName).getOrThrow()
        store.saveCategoryDetails(
            meals = response,
            categoryName = categoryName,
            area = ""
        )
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        store.saveCategoryDetails(
            meals = response,
            categoryName = "",
            area = area
        )
    }
}
