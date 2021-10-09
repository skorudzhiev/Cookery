package app.cookery.domain.observers

import app.cookery.data.entities.categories.MealsCollection
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMealsCollection @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveMealsCollection.Params, List<MealsCollection>>() {

    override fun createObservable(params: Params): Flow<List<MealsCollection>> {
        return repository.observeMealsCollection()
    }

    data class Params(val categoryId: Int = 0)
}
