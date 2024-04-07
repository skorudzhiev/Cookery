package app.cookery.domain.interactors

import app.cookery.AppCoroutineDispatchers
import app.cookery.domain.Interactor
import app.cookery.domain.repositories.RandomRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateRandomMeal @Inject constructor(
    private val randomRepository: RandomRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Unit>() {

    override suspend fun doWork(params: Unit) = withContext(dispatchers.io) {
        randomRepository.getRandomMeal()
    }
}
