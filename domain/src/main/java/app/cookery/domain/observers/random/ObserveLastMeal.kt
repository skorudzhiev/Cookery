package app.cookery.domain.observers.random

import app.cookery.domain.SubjectInteractor
import app.cookery.domain.model.MealDetails
import app.cookery.domain.repositories.RandomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLastMeal @Inject constructor(
    private val randomRepository: RandomRepository
) : SubjectInteractor<Unit, MealDetails?>() {

    override fun createObservable(params: Unit): Flow<MealDetails?> = randomRepository.observeLastMeal()
}
