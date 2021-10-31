package app.cookery.domain.observers

import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategoryDetailsByArea @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveCategoryDetailsByArea.Params, CategoryDetails>() {

    override fun createObservable(params: Params): Flow<CategoryDetails> {
        return repository.observeCategoryDetailsByArea(params.area)
    }

    data class Params(val area: String)
}
