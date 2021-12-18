package app.cookery.domain.observers

import app.cookery.data.entities.relations.AreaWithCategoryDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAreaWithCategoryDetails @Inject constructor(
    private val repository: CategoriesRepository
) : SubjectInteractor<ObserveAreaWithCategoryDetails.Params, List<AreaWithCategoryDetails>>() {

    override fun createObservable(params: Params): Flow<List<AreaWithCategoryDetails>> {
        return repository.observeAreaWithCategoryDetails(params.areaName)
    }

    data class Params(val areaName: String)
}
