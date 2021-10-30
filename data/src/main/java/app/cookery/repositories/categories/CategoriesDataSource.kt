package app.cookery.repositories.categories

import app.cookery.data.Result
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails

interface CategoriesDataSource {
    suspend fun getAllMealCategories(): Result<List<Category>>
    suspend fun getMealsByCategory(category: String): Result<List<CategoryDetails>>
    suspend fun getMealsByArea(area: String): Result<List<CategoryDetails>>
    suspend fun getMealAreas(): Result<List<Area>>
}
