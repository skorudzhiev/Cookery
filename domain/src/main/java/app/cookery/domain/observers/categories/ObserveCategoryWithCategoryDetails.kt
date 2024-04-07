package app.cookery.domain.observers.categories

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.CategoryWithCategoryDetails
import app.cookery.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategoryWithCategoryDetails @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveCategoryWithCategoryDetails.Params, List<CategoryWithCategoryDetails>>() {

    override fun createObservable(params: Params): Flow<List<CategoryWithCategoryDetails>> {
        return repository.observeCategoryWithCategoryDetails(params.category)
    }

    data class Params(val category: String)
}
