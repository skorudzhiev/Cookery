package app.cookery.domain.observers

import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategoryDetailsByName @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveCategoryDetailsByName.Params, CategoryDetails>() {

    override fun createObservable(params: Params): Flow<CategoryDetails> {
        return repository.observeCategoryDetailsByName(params.category)
    }

    data class Params(val category: String)
}
