package app.cookery.repositories.details

import javax.inject.Inject

class MealRepository @Inject constructor(
    private val dataSource: MealDataSource,
    private val mealStore: MealStore
) {

    suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        mealStore.saveMeal(response)
    }
}
