package app.cookery.domain.repositories

import app.cookery.data.Result
import app.cookery.domain.model.MealDetails

interface SearchRepository {

    suspend fun searchMealByName(mealName: String): Result<List<MealDetails>>
}
