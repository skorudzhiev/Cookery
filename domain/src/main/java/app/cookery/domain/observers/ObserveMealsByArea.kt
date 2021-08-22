package app.cookery.domain.observers

import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMealsByArea @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveMealsByArea.Params, FilterMealsByArea>() {

    override fun createObservable(params: Params): Flow<FilterMealsByArea> {
        return repository.observeMealsFilteredByArea(params.area)
    }

    data class Params(val area: String)
}
