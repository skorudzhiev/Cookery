package app.cookery.repositories.categories.areas

import app.cookery.repositories.categories.CategoriesDataSource
import app.cookery.repositories.categories.CategoriesStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val store: AreaStore,
    private val categoriesStore: CategoriesStore
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
        categoriesStore.saveCategoryDetails(
            meals = response,
            categoryName = "",
            area = area
        )
    }
}
