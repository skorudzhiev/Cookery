package app.cookery.domain.observers

import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoriteMeals @Inject constructor(
    private val repository: FavoritesRepository
) : SubjectInteractor<Unit, List<CategoryDetails>>() {

    override fun createObservable(params: Unit): Flow<List<CategoryDetails>> =
        repository.observeFavoriteMeals()
}
