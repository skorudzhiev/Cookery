package app.cookery.repositories.areas

import app.cookery.repositories.categories.CategoriesLocalDataSource
import app.cookery.repositories.categories.remote.CategoriesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaRepository @Inject constructor(
    private val dataSource: CategoriesRemoteDataSource,
    private val store: AreaLocalDataSource,
    private val categoriesLocalDataSource: CategoriesLocalDataSource
) {

    fun observeRandomAreaMeals() = store.observeRandomAreaMeals()

    fun observeAreaWithCategoryDetails(areaName: String) =
        store.observeAreaWithCategoryDetails(areaName)

    fun observeAreaMeals() = store.observeAreaMeals()

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        store.saveAreaMeals(response)
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        categoriesLocalDataSource.saveCategoryDetails(
            meals = response,
            categoryName = "",
            area = area
        )
    }
}
