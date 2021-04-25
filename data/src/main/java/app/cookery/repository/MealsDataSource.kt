package app.cookery.repository

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult

class MealsDataSource(
    private val service: TheMealDbApi
) : MealsSource {
    override suspend fun getCategories(): Result<List<String>> {
        return service.getMealCategories()
            .executeWithRetry()
            .toResult()
    }
}
