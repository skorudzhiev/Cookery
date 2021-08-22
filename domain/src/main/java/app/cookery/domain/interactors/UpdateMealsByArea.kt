package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMealsByArea @Inject constructor(
    private val repository: CategoriesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateMealsByArea.Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchMealsByArea(params.area)
        }
    }

    data class Params(val area: String)
}
