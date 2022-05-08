package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.repositories.favorites.FavoritesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateFavoriteMeals @Inject constructor(
    private val repository: FavoritesRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateFavoriteMeals.Params>() {

    override suspend fun doWork(params: Params) = withContext(dispatchers.io) {
        repository.saveFavoriteMeal(params.mealId)
    }

    data class Params(val mealId: String)
}
