package app.cookery.repositories.categories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val store: CategoriesStore
) {

    fun observeRandomCategoryDetails() = store.observeRandomCategoryMeals()
    fun observeAllMealCategories() = store.observeAllMealCategories()
    fun observeCategoryWithCategoryDetails(categoryName: String) =
        store.observeCategoryWithCategoryDetails(categoryName)

    suspend fun fetchAllMealCategories() {
        val response = dataSource.getAllMealCategories().getOrThrow()
        store.saveAllMealCategories(response)
    }

    suspend fun fetchMealsByCategory(categoryName: String) {
        val response = dataSource.getMealsByCategory(categoryName).getOrThrow()
        store.saveCategoryDetails(
            meals = response,
            categoryName = categoryName,
            area = ""
        )
    }
}
