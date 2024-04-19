package app.cookery.domain.observers.search

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLastOpenedMeals @Inject constructor(
    private val repository: SearchRepository
) : SubjectInteractor<Unit, List<CategoryDetails>>() {

    override fun createObservable(params: Unit): Flow<List<CategoryDetails>> =
        repository.observeLastOpenedMeals()
}
