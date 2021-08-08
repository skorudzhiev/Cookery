package app.cookery.repositories.categories

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.categories.ListedMealsByArea
import app.cookery.data.entities.categories.ListedMealsByCategory
import app.cookery.data.entities.categories.ListedMealsByFilter
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

class TheMealDbCategoryDataSource @Inject constructor(
    private val theMealDbApi: TheMealDbApi
) : CategoriesDataSource {
    override suspend fun getMealsCategories(): Result<ListedMealsByCategory> {
        return theMealDbApi.getMealCategories()
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealsByCategory(category: String): Result<ListedMealsByFilter> {
        return theMealDbApi.getMealByCategory(category)
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealAreas(): Result<ListedMealsByArea> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealsByArea(area: String): Result<ListedMealsByFilter> {
        return theMealDbApi.getMealByArea(area)
            .executeWithRetry()
            .toResult()
    }
}
