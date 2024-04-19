package app.cookery.domain.usecases.search

import app.cookery.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchMealByNameUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(mealName: String) = repository.searchMealByName(mealName)
}
