package app.cookery.repositories.categories

import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.CategoryDetailsDao
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val areaDao: AreaDao,
    private val categoriesDao: CategoriesDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomCategoryMeals(): Flow<List<CategoryDetails>> = categoryDetailsDao.getRandomCategoryMeals()
    fun observeRandomAreaMeals(): Flow<List<CategoryDetails>> = categoryDetailsDao.getRandomAreaMeals()
    fun observeAllMealCategories(): Flow<List<Category>> = categoriesDao.getAllMealCategories()
    fun observeAreaMeals(): Flow<List<Area>> = areaDao.getMealAreas()

    fun observeCategoryDetailsByName(categoryName: String): Flow<CategoryDetails> {
        return categoryDetailsDao.getCategoryDetailsByName(categoryName = categoryName)
    }

    fun observeCategoryDetailsByArea(area: String): Flow<CategoryDetails> {
        return categoryDetailsDao.getCategoryDetailsByArea(area = area)
    }

    suspend fun saveAllMealCategories(categories: List<Category>) = categoriesDao.insertCategories(categories)
    suspend fun saveAreaMeals(areas: List<Area>) = areaDao.insertAreas(areas)

    suspend fun saveCategoryDetails(
        meals: List<CategoryDetails>,
        categoryName: String,
        area: String
    ) = categoryDetailsDao.insertCategoryDetails(meals, categoryName, area)
}
