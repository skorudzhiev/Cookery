package app.cookery.repositories.categories.remote

import app.cookery.data.Result
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails

interface CategoriesRemoteDataSource {

    suspend fun getAllMealCategories(): Result<List<Category>>
    suspend fun getMealsByCategory(category: String): Result<List<CategoryDetails>>
    suspend fun getMealsByArea(area: String): Result<List<CategoryDetails>>
    suspend fun getMealAreas(): Result<List<Area>>
}
