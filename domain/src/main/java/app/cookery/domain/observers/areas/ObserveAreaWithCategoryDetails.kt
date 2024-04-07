package app.cookery.domain.observers.areas

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.AreaWithCategoryDetails
import app.cookery.domain.repositories.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAreaWithCategoryDetails @Inject constructor(
    private val repository: AreaRepository
) : SubjectInteractor<ObserveAreaWithCategoryDetails.Params, List<AreaWithCategoryDetails>>() {

    override fun createObservable(params: Params): Flow<List<AreaWithCategoryDetails>> {
        return repository.observeAreaWithCategoryDetails(params.areaName)
    }

    data class Params(val areaName: String)
}
