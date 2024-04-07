package app.cookery.domain.observers.categories

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRandomCategoryMeals @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<Unit, List<CategoryDetails>>() {

    override fun createObservable(params: Unit): Flow<List<CategoryDetails>> {
        return repository.observeRandomCategoryDetails()
    }
}
