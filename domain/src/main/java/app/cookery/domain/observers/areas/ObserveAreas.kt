package app.cookery.domain.observers.areas

import app.cookery.data.entities.categories.Area
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.areas.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAreas @Inject constructor(
    private val repository: AreaRepository
) : SubjectInteractor<ObserveAreas.Params, List<Area>>() {

    override fun createObservable(params: Params): Flow<List<Area>> {
        return repository.observeAreaMeals()
    }

    data class Params(val area: String? = null)
}
