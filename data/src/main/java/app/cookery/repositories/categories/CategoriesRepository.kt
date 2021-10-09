package app.cookery.repositories.categories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val dataSource: CategoriesDataSource,
    private val store: CategoriesStore
) {

    fun observeMealsCollection() = store.observeMealsCollection()

    fun observeAllMealCategories() = store.observeAllMealCategories()

    fun observeAreaMeals() = store.observeAreaMeals()

    fun observeMealsFilteredByCategory(category: String) = store.observeMealsFilteredByCategory(category)

    fun observeMealsFilteredByArea(area: String) = store.observeMealsFilteredByArea(area)

    suspend fun fetchAllMealCategories() {
        val response = dataSource.getAllMealCategories().getOrThrow()
        store.saveAllMealCategories(response)
    }

    suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        store.saveAreaMeals(response)
    }

    suspend fun fetchMealsByCategory(category: String) {
        val response = dataSource.getMealsByCategory(category).getOrThrow()
        store.saveMealsByCategory(category, response)
    }

    suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        store.saveMealsByArea(area, response)
    }
}
