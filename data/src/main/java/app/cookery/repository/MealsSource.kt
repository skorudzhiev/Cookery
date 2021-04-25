package app.cookery.repository

import app.cookery.data.Result

interface MealsSource {

    suspend fun getCategories(): Result<List<String>>
}
