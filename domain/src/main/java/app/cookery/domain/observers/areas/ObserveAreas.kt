package app.cookery.domain.observers.areas

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.Area
import app.cookery.domain.repositories.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAreas @Inject constructor(
    private val repository: AreaRepository
) : SubjectInteractor<Unit, List<Area>>() {

    override fun createObservable(params: Unit): Flow<List<Area>> {
        return repository.observeAreaMeals()
    }
}
