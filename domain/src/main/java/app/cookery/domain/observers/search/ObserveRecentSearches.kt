package app.cookery.domain.observers.search

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRecentSearches @Inject constructor(
    private val repository: SearchRepository
) : SubjectInteractor<Unit, List<String>>() {

    override fun createObservable(params: Unit): Flow<List<String>> =
        repository.observeRecentSearches()
}
