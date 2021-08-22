package app.cookery.repositories.categories

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

class TheMealDbCategoryDataSource @Inject constructor(
    private val theMealDbApi: TheMealDbApi
) : CategoriesDataSource {
    override suspend fun getAllMealCategories(): Result<AllMealCategories> {
        return theMealDbApi.getMealCategories()
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealsByCategory(category: String): Result<FilterMealsByCategory> {
        return theMealDbApi.getMealByCategory(category)
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealAreas(): Result<Areas> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealsByArea(area: String): Result<FilterMealsByArea> {
        return theMealDbApi.getMealByArea(area)
            .executeWithRetry()
            .toResult()
    }
}
