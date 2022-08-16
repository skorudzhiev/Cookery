package app.cookery.domain.interactors.categories

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.domain.interactors.categories.UpdateAllMealCategories.Params
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateAllMealCategories @Inject constructor(
    private val repository: CategoriesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchAllMealCategories()
        }
    }

    data class Params(val categoryId: Long = 0)
}
