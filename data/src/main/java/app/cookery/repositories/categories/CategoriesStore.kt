package app.cookery.repositories.categories

import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.MealsByAreaDao
import app.cookery.data.daos.categories.MealsByFilterDao
import app.cookery.data.entities.categories.ListedMealsByArea
import app.cookery.data.entities.categories.ListedMealsByCategory
import app.cookery.data.entities.categories.ListedMealsByFilter
import javax.inject.Inject

class CategoriesStore @Inject constructor(
    private val categoriesDao: CategoriesDao,
    private val mealsByAreaDao: MealsByAreaDao,
    private val mealsByFilterDao: MealsByFilterDao,
) {

    suspend fun saveMealsCategoryTypes(categories: ListedMealsByCategory) = categoriesDao.insertOrUpdate(categories)
    suspend fun saveMealsByArea(meals: ListedMealsByArea) = mealsByAreaDao.insertOrUpdate(meals)
    suspend fun saveMealsByFilter(category: ListedMealsByFilter) = mealsByFilterDao.insertOrUpdate(category)
}
