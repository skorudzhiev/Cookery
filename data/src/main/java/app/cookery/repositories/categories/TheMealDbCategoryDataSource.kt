package app.cookery.repositories.categories

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.FilterMealsByArea
import app.cookery.data.entities.categories.FilterMealsByCategory
import app.cookery.data.mappers.AreasToArea
import app.cookery.data.mappers.CategoriesToCategory
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

class TheMealDbCategoryDataSource @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val areaMapper: AreasToArea,
    private val categoryMapper: CategoriesToCategory
) : CategoriesDataSource {
    override suspend fun getAllMealCategories(): Result<List<Category>> {
        return theMealDbApi.getMealCategories()
            .executeWithRetry()
            .toResult(categoryMapper::map)
    }

    override suspend fun getMealsByCategory(category: String): Result<FilterMealsByCategory> {
        return theMealDbApi.getMealByCategory(category)
            .executeWithRetry()
            .toResult()
    }

    override suspend fun getMealAreas(): Result<List<Area>> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult(areaMapper::map)
    }

    override suspend fun getMealsByArea(area: String): Result<FilterMealsByArea> {
        return theMealDbApi.getMealByArea(area)
            .executeWithRetry()
            .toResult()
    }
}
