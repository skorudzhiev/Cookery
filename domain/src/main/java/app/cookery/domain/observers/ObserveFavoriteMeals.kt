package app.cookery.domain.observers

import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoriteMeals @Inject constructor(
    private val repository: FavoritesRepository
) : SubjectInteractor<ObserveFavoriteMeals.Params, List<CategoryDetails>>() {

    override fun createObservable(params: Params): Flow<List<CategoryDetails>> =
        repository.observeFavoriteMeals()

    data class Params(val favoriteMealId: String? = null)
}
