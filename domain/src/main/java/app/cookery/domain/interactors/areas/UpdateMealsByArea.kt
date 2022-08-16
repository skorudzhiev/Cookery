package app.cookery.domain.interactors.areas

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.categories.areas.AreaRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMealsByArea @Inject constructor(
    private val repository: AreaRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateMealsByArea.Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchMealsByArea(params.area)
        }
    }

    data class Params(val area: String)
}
