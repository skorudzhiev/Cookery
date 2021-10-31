package app.cookery.domain.observers

import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRandomCategoryMeals @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveRandomCategoryMeals.Params, List<CategoryDetails>>() {

    override fun createObservable(params: Params): Flow<List<CategoryDetails>> {
        return repository.observeRandomCategoryDetails()
    }

    data class Params(val meals: Int? = null)
}
