package app.cookery.domain.observers

import app.cookery.data.entities.MealDetails
import app.cookery.domain.SubjectInteractor
import app.cookery.repositories.details.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMealDetails @Inject constructor(
    private val repository: MealRepository
) : SubjectInteractor<ObserveMealDetails.Params, MealDetails>() {

    override fun createObservable(params: Params): Flow<MealDetails> {
        return repository.observeMealDetails(params.mealId)
    }

    data class Params(val mealId: String)
}
