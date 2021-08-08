package app.cookery

import app.cookery.data.daos.categories.CategoriesDao

interface CookeryDatabase {
    fun categoriesDao(): CategoriesDao
}
