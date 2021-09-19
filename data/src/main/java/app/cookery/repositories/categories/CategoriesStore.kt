package app.cookery.repositories.categories

import app.cookery.data.daos.categories.AreasDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.FilterByAreaDao
import app.cookery.data.daos.categories.FilterByCategoryDao
import app.cookery.data.daos.categories.MealsCollectionDao
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.data.entities.categories.MealsCollection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val mealsCollectionDao: MealsCollectionDao,
    private val categoriesDao: CategoriesDao,
    private val areasDao: AreasDao,
    private val filterByCategoryDao: FilterByCategoryDao,
    private val filterByAreaDao: FilterByAreaDao
) {

    fun observeMealsCollection(): Flow<List<MealsCollection>> {
        return mealsCollectionDao.getMealsCollection()
    }

    fun observeAllMealCategories(): Flow<AllMealCategories> {
        return categoriesDao.getAllMealCategories()
    }

    fun observeAreaMeals(): Flow<Areas> {
        return areasDao.getMealAreas()
    }

    fun observeMealsFilteredByCategory(category: String): Flow<FilterMealsByCategory> {
        return filterByCategoryDao.getMealsFilteredByCategory(category)
    }

    fun observeMealsFilteredByArea(area: String): Flow<FilterMealsByArea> {
        return filterByAreaDao.getMealsFilteredByArea(area)
    }

    suspend fun saveMealsCollection(mealsCollection: MealsCollection) = mealsCollectionDao.insert(mealsCollection)
    suspend fun saveAllMealCategories(categories: AllMealCategories) = categoriesDao.insert(categories)
    suspend fun saveAreaMeals(meals: Areas) = areasDao.insert(meals)

    suspend fun saveMealsByCategory(category: String, meals: FilterMealsByCategory) {
        meals.category = category
        filterByCategoryDao.insert(meals)
    }

    suspend fun saveMealsByArea(area: String, meals: FilterMealsByArea) {
        meals.area = area
        filterByAreaDao.insert(meals)
    }
}
