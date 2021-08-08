package app.cookery.repositories.categories

import app.cookery.data.Result
import app.cookery.data.entities.categories.ListedMealsByArea
import app.cookery.data.entities.categories.ListedMealsByCategory
import app.cookery.data.entities.categories.ListedMealsByFilter

interface CategoriesDataSource {
    suspend fun getMealsCategories(): Result<ListedMealsByCategory>
    suspend fun getMealsByCategory(category: String): Result<ListedMealsByFilter>
    suspend fun getMealAreas(): Result<ListedMealsByArea>
    suspend fun getMealsByArea(area: String): Result<ListedMealsByFilter>
}
