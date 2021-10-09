package app.cookery.repositories.categories

import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.FilterByAreaDao
import app.cookery.data.daos.categories.FilterByCategoryDao
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.CollectionType
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.data.entities.categories.MealsCollection
import app.cookery.data.models.Areas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val areaDao: AreaDao,
    private val categoriesDao: CategoriesDao,
    private val filterByCategoryDao: FilterByCategoryDao,
    private val filterByAreaDao: FilterByAreaDao
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

    fun observeAllMealCategories(): Flow<AllMealCategories> {
        return categoriesDao.getAllMealCategories()
    }

    fun observeAreaMeals(): Flow<Areas> {
        return flowOf(Areas(areas = listOf()))
    }

    fun observeMealsFilteredByCategory(category: String): Flow<FilterMealsByCategory> {
        return filterByCategoryDao.getMealsFilteredByCategory(category)
    }

    fun observeMealsFilteredByArea(area: String): Flow<FilterMealsByArea> {
        return filterByAreaDao.getMealsFilteredByArea(area)
    }

//    suspend fun saveMealsCollection(mealsCollection: MealsCollection) = mealsCollectionDao.insert(mealsCollection)
    suspend fun saveAllMealCategories(categories: AllMealCategories) = categoriesDao.insert(categories)
    suspend fun saveAreaMeals(areas: List<Area>) = areaDao.insertAreas(areas)

    suspend fun saveMealsByCategory(category: String, meals: FilterMealsByCategory) {
        meals.category = category
        filterByCategoryDao.insert(meals)
    }

    suspend fun saveMealsByArea(area: String, meals: FilterMealsByArea) {
        meals.area = area
        filterByAreaDao.insert(meals)
    }
}
