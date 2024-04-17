package app.cookery.db

import app.cookery.db.daos.FavoritesDao
import app.cookery.db.daos.MealDao
import app.cookery.db.daos.categories.AreaDao
import app.cookery.db.daos.categories.CategoriesDao
import app.cookery.db.daos.categories.CategoryDetailsDao
import app.cookery.db.daos.search.LastOpenedMealsDao
import app.cookery.db.daos.search.RecentSearchesDao

interface CookeryDatabase {
    fun areaDao(): AreaDao
    fun categoriesDao(): CategoriesDao
    fun categoryDetailsDao(): CategoryDetailsDao
    fun mealDetailsDao(): MealDao
    fun favoritesDao(): FavoritesDao
    fun recentSearchesDao(): RecentSearchesDao
    fun lastOpenedMealsDao(): LastOpenedMealsDao
}
