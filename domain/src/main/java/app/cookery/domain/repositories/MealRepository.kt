package app.cookery.domain.repositories

import app.cookery.domain.model.MealDetails
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun observeMealDetails(mealId: String): Flow<MealDetails?>
    suspend fun fetchMealDetails(mealId: String)
    fun isMealMarkedAsFavorite(mealId: String): Flow<String>
    suspend fun updateFavoriteMeal(mealId: String, isFavorite: Boolean)
}
