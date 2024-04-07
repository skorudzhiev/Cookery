package app.cookery.domain.observers.categories

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.Category
import app.cookery.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategories @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<Unit, List<Category>>() {

    override fun createObservable(params: Unit): Flow<List<Category>> {
        return repository.observeAllMealCategories()
    }
}
