package app.cookery.repositories.categories.remote

import app.cookery.data.Result
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.db.entities.categories.CategoryEntity

interface CategoriesRemoteDataSource {

    suspend fun getAllMealCategories(): Result<List<CategoryEntity>>
    suspend fun getMealsByCategory(category: String): Result<List<CategoryDetailsEntity>>
    suspend fun getMealsByArea(area: String): Result<List<CategoryDetailsEntity>>
    suspend fun getMealAreas(): Result<List<AreaEntity>>
}
