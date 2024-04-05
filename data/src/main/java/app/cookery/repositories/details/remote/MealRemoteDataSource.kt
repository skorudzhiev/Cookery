package app.cookery.repositories.details.remote

import app.cookery.data.Result
import app.cookery.db.entities.MealDetails

interface MealRemoteDataSource {
    suspend fun getMealDetails(mealId: String): Result<List<MealDetails>>
}
