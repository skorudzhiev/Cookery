package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateAreas @Inject constructor(
    private val repository: CategoriesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateAreas.Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchAllMealAreas()
        }
    }

    data class Params(val area: String? = null)
}
