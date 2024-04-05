package app.cookery.repositories.categories

import app.cookery.repositories.categories.remote.CategoriesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesRemoteDataSource,
    private val store: CategoriesLocalDataSource
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
