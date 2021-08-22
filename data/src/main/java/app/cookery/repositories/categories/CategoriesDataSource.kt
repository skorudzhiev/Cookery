package app.cookery.repositories.categories

import app.cookery.data.Result
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory

interface CategoriesDataSource {
    suspend fun getAllMealCategories(): Result<AllMealCategories>
    suspend fun getMealsByCategory(category: String): Result<FilterMealsByCategory>
    suspend fun getMealAreas(): Result<Areas>
    suspend fun getMealsByArea(area: String): Result<FilterMealsByArea>
}
