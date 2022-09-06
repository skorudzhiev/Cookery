package app.cookery.repositories.categories

import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.CategoryDetailsDao
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.relations.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomCategoryMeals(): Flow<List<CategoryDetails>> = categoryDetailsDao.getRandomCategoryMeals()

    fun observeAllMealCategories(): Flow<List<Category>> = categoriesDao.getAllMealCategories()

    fun observeCategoryWithCategoryDetails(
        categoryName: String
    ): Flow<List<CategoryWithCategoryDetails>> =
        categoriesDao.getCategoryWithCategoryDetails(categoryName)

    suspend fun saveAllMealCategories(categories: List<Category>) = categoriesDao.insertDetails(categories)

    suspend fun saveCategoryDetails(
        meals: List<CategoryDetails>,
        categoryName: String,
        area: String
    ) = categoryDetailsDao.insertCategoryDetails(meals, categoryName, area)
}
