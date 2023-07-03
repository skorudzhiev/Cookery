package app.cookery.repositories.random

import app.cookery.data.entities.MealDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RandomRepository {

    /**
     * @param shouldUseOfflinePolicy if true, the repository will use a different policy to obtain a random meal.
     *
     * The default policy is to obtain a random meal from
     * @see RandomStore
     * If the existing entries in MealStore are at least 30,
     * and the retrieved meal is already is not present neither in FavoritesStore nor in RandomStore,
     * then the meal is saved in RandomStore and emitted.
     *
     * The alternative policy is to obtain a random meal from MealStore and emit it only if its not present in FavoritesStore.
     * This policy should be used only in cases when there is no internet connection, as a result of the first attempt to obtain a random meal.
     *
     * @return a [Flow] of [MealDetails] that emits a new value every time a new random meal is obtained.
     */
    fun observeRandomMeal(shouldUseOfflinePolicy: Boolean = false): Flow<MealDetails>
}

class RandomRepositoryImpl @Inject constructor(
    private val randomDataSource: RandomDataSource,
) : RandomRepository {

    override fun observeRandomMeal(shouldFollowStrictPolicy: Boolean): Flow<MealDetails> {
        TODO("Not yet implemented")
    }

    private suspend fun getRandomMeal() {
        val response = randomDataSource.getRandomMeal().getOrThrow()
        // Check if the response is throwable related to internet connection
    }
}
