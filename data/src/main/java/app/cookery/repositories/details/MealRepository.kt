package app.cookery.repositories.details

import javax.inject.Inject

class MealRepository @Inject constructor(
    private val dataSource: MealDataSource
) {

    suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        // TODO: 20.08.21 Save data in the DB
    }
}
