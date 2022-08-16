package app.cookery.domain.observers.categories

import app.cookery.data.entities.categories.Category
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategories @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveCategories.Params, List<Category>>() {

    override fun createObservable(params: Params): Flow<List<Category>> {
        return repository.observeAllMealCategories()
    }

    data class Params(val category: String? = null)
}
