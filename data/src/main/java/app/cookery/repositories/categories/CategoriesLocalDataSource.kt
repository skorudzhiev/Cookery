package app.cookery.repositories.categories

import app.cookery.db.daos.categories.CategoriesDao
import app.cookery.db.daos.categories.CategoryDetailsDao
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.db.entities.relations.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesLocalDataSource @Inject constructor(
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
