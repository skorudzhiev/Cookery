package app.cookery.repositories.categories

import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.CategoryDetailsDao
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.categories.CollectionType
import app.cookery.data.entities.categories.MealsCollection
import app.cookery.data.models.Areas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val areaDao: AreaDao,
    private val categoriesDao: CategoriesDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeMealsCollection(): Flow<List<MealsCollection>> {
        return flowOf(
            listOf(
                MealsCollection(
                    collectionName = "",
                    meals = listOf(),
                    categories = listOf(),
                    areas = listOf(),
                    type = CollectionType.Categories
                )
            )
        )
    }

    fun observeAllMealCategories(): Flow<List<Category>> {
        return categoriesDao.getAllMealCategories()
    }

    fun observeAreaMeals(): Flow<Areas> {
        return flowOf(Areas(areas = listOf()))
    }

    suspend fun saveAllMealCategories(categories: List<Category>) = categoriesDao.insertCategories(categories)
    suspend fun saveAreaMeals(areas: List<Area>) = areaDao.insertAreas(areas)

    suspend fun saveCategoryDetails(
        meals: List<CategoryDetails>,
        categoryName: String,
        area: String
    ) = categoryDetailsDao.insertCategoryDetails(meals, categoryName, area)
}
