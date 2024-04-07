package app.cookery.repositories.categories

import app.cookery.db.daos.categories.CategoriesDao
import app.cookery.db.daos.categories.CategoryDetailsDao
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.domain.model.CategoryWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesLocalDataSource @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomCategoryMeals(): Flow<List<CategoryDetailsEntity>> = categoryDetailsDao.getRandomCategoryMeals()

    fun observeAllMealCategories(): Flow<List<CategoryEntity>> = categoriesDao.getAllMealCategories()

    fun observeCategoryWithCategoryDetails(
        categoryName: String
    ): Flow<List<CategoryWithCategoryDetails>> =
        categoriesDao.getCategoryWithCategoryDetails(categoryName)

    suspend fun saveAllMealCategories(categories: List<CategoryEntity>) = categoriesDao.insertDetails(categories)

    suspend fun saveCategoryDetails(
        meals: List<CategoryDetailsEntity>,
        categoryName: String,
        area: String
    ) = categoryDetailsDao.insertCategoryDetails(meals, categoryName, area)
}
