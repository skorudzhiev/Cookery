package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.areas.AreaRepository
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

private val categoryTypes = listOf("Pasta", "Starter", "Dessert")
private val areaTypes = listOf("Italian", "Thai", "Mexican", "American")

class InitializeHomeScreenData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val areasRepository: AreaRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<InitializeHomeScreenData.Params>() {
    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            categoriesRepository.fetchAllMealCategories()
            areasRepository.fetchAllMealAreas()
            fetchInitialCategories()
            fetchInitialAreas()
        }
    }

    private suspend fun fetchInitialCategories() {
        for (category in categoryTypes) {
            categoriesRepository.fetchMealsByCategory(category)
        }
    }

    private suspend fun fetchInitialAreas() {
        for (area in areaTypes) {
            areasRepository.fetchMealsByArea(area)
        }
    }

    data class Params(val _val: Long = 0)
}
