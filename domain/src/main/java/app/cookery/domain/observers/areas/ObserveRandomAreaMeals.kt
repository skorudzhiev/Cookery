package app.cookery.domain.observers.areas

import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.areas.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRandomAreaMeals @Inject constructor(
    private val repository: AreaRepository
) : SubjectInteractor<Unit, List<CategoryDetails>>() {

    override fun createObservable(params: Unit): Flow<List<CategoryDetails>> {
        return repository.observeRandomAreaMeals()
    }
}
