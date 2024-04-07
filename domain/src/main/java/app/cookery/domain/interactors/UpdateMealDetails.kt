package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.domain.repositories.MealRepository
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

    suspend fun updateFavoriteMeal(params: Params, isMarkedAsFavorite: Boolean) {
        repository.updateFavoriteMeal(
            mealId = params.mealId,
            isFavorite = isMarkedAsFavorite
        )
    }

    data class Params(val mealId: String)
}
