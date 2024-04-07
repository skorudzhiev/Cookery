package app.cookery.domain.repositories

import app.cookery.domain.model.Category
import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.model.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun observeRandomCategoryDetails(): Flow<List<CategoryDetails>>
    fun observeAllMealCategories(): Flow<List<Category>>
    fun observeCategoryWithCategoryDetails(categoryName: String): Flow<List<CategoryWithCategoryDetails>>

    suspend fun fetchAllMealCategories()
    suspend fun fetchMealsByCategory(categoryName: String)
}
