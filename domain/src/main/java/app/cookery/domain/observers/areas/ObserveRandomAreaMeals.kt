package app.cookery.domain.observers.areas

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.repositories.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRandomAreaMeals @Inject constructor(
    private val repository: AreaRepository
) : SubjectInteractor<Unit, List<CategoryDetails>>() {

    override fun createObservable(params: Unit): Flow<List<CategoryDetails>> {
        return repository.observeRandomAreaMeals()
    }
}
