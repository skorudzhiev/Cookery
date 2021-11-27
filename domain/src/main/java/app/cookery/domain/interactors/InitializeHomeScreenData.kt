package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val categoryTypes = listOf("Pasta", "Starter", "Dessert")
private val areaTypes = listOf("Italian", "Thai", "Mexican", "American")

class InitializeHomeScreenData @Inject constructor(
    private val repository: CategoriesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<InitializeHomeScreenData.Params>() {
    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchAllMealCategories()
            repository.fetchAllMealAreas()
            fetchInitialCategories()
            fetchInitialAreas()
        }
    }

    private suspend fun fetchInitialCategories() {
        for (category in categoryTypes) {
            repository.fetchMealsByCategory(category)
        }
    }

    private suspend fun fetchInitialAreas() {
        for (area in areaTypes) {
            repository.fetchMealsByArea(area)
        }
    }

    data class Params(val _val: Long = 0)
}
