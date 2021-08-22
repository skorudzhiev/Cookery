package app.cookery

import app.cookery.data.daos.MealDao
import app.cookery.data.daos.categories.AreasDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.FilterByAreaDao
import app.cookery.data.daos.categories.FilterByCategoryDao

interface CookeryDatabase {
    fun areasDao(): AreasDao
    fun categoriesDao(): CategoriesDao
    fun filterByAreaDao(): FilterByAreaDao
    fun filterByCategoryDao(): FilterByCategoryDao
    fun mealDetailsDao(): MealDao
}
