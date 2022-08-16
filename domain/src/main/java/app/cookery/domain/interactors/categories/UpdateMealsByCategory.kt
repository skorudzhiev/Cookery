package app.cookery.domain.interactors.categories

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMealsByCategory @Inject constructor(
    private val repository: CategoriesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateMealsByCategory.Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchMealsByCategory(params.categoryName)
        }
    }

    data class Params(val categoryName: String)
}
