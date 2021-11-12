package app.cookery.domain.observers

import app.cookery.data.entities.relations.CategoryWithCategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
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
