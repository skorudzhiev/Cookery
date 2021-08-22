package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.details.MealRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMealDetails @Inject constructor(
    private val repository: MealRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateMealDetails.Params>() {

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            repository.fetchMealDetails(params.mealId)
        }
    }

    data class Params(val mealId: String)
}
