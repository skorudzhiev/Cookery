package app.cookery.repositories.details

import app.cookery.data.Result
import app.cookery.data.entities.MealDetails

interface MealDataSource {
    suspend fun getMealDetails(mealId: String): Result<List<MealDetails>>
}
