package app.cookery.domain.usecases.search

import app.cookery.domain.repositories.SearchRepository
import javax.inject.Inject

class CleanRecentSearchResultsUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke() = repository.cleanRecentSearchResults()
}