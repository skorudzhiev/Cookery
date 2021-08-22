package app.cookery.domain.observers

import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMealsByCategory @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveMealsByCategory.Params, FilterMealsByCategory>() {

    override fun createObservable(params: Params): Flow<FilterMealsByCategory> {
        return repository.observeMealsFilteredByCategory(params.category)
    }

    data class Params(val category: String)
}
