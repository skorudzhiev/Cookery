package app.cookery

import app.cookery.data.daos.FavoritesDao
import app.cookery.data.daos.MealDao
import app.cookery.data.daos.RandomMealDao
import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoriesDao
import app.cookery.data.daos.categories.CategoryDetailsDao

interface CookeryDatabase {
    fun areaDao(): AreaDao
    fun categoriesDao(): CategoriesDao
    fun categoryDetailsDao(): CategoryDetailsDao
    fun mealDetailsDao(): MealDao
    fun favoritesDao(): FavoritesDao
    fun randomMealDao(): RandomMealDao
}
