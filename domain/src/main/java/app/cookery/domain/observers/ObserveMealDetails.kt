package app.cookery.domain.observers

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.MealDetails
import app.cookery.domain.repositories.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMealDetails @Inject constructor(
    private val repository: MealRepository
) : SubjectInteractor<ObserveMealDetails.Params, MealDetails?>() {

    override fun createObservable(params: Params): Flow<MealDetails?> {
        return repository.observeMealDetails(params.mealId)
    }

    fun isMarkedAsFavorite(params: Params): Flow<String> =
        repository.isMealMarkedAsFavorite(params.mealId)

    data class Params(val mealId: String)
}
