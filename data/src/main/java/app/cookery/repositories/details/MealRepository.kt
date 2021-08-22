package app.cookery.repositories.details

import javax.inject.Inject

class MealRepository @Inject constructor(
    private val dataSource: MealDataSource,
    private val store: MealStore
) {

    fun observeMealDetails(mealId: String) = store.observeMeal(mealId)

    suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        store.saveMeal(response)
    }
}
