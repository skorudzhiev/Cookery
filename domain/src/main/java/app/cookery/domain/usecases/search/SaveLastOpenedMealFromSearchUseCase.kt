package app.cookery.domain.usecases.search

import app.cookery.domain.repositories.SearchRepository
import javax.inject.Inject

class SaveLastOpenedMealFromSearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(mealId: String) = repository.storeLastOpenedMealFromSearchResults(mealId)
}
