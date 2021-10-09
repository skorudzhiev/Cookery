package app.cookery

import app.cookery.data.daos.MealDao
import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.FilterByAreaDao
import app.cookery.data.daos.categories.FilterByCategoryDao

interface CookeryDatabase {
    fun areaDao(): AreaDao
    fun categoriesDao(): CategoriesDao
    fun filterByAreaDao(): FilterByAreaDao
    fun filterByCategoryDao(): FilterByCategoryDao
    fun mealDetailsDao(): MealDao
}
