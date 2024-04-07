package app.cookery.domain.repositories

import app.cookery.domain.model.MealDetails
import kotlinx.coroutines.flow.Flow

interface RandomRepository {

    fun observeLastMeal(): Flow<MealDetails?>
    suspend fun getRandomMeal()
}
