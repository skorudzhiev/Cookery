package app.cookery.domain.observers

import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAllMealCategories @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveAllMealCategories.Params, AllMealCategories>() {

    override fun createObservable(params: Params): Flow<AllMealCategories> {
        return repository.observeAllMealCategories()
    }

    data class Params(val categoryId: Int = 0)
}
