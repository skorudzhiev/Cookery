package app.cookery.repositories.categories

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.mappers.AreasToArea
import app.cookery.data.mappers.CategoriesToCategory
import app.cookery.data.mappers.MealsToCategoryDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import javax.inject.Inject

class TheMealDbCategoryDataSource @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val areaMapper: AreasToArea,
    private val categoryMapper: CategoriesToCategory,
    private val categoryDetailsMapper: MealsToCategoryDetails
) : CategoriesDataSource {
    override suspend fun getAllMealCategories(): Result<List<Category>> {
        return theMealDbApi.getMealCategories()
            .executeWithRetry()
            .toResult(categoryMapper::map)
    }

    override suspend fun getMealsByCategory(category: String): Result<List<CategoryDetails>> {
        return theMealDbApi.getMealByCategory(category)
            .executeWithRetry()
            .toResult(categoryDetailsMapper::map)
    }

    override suspend fun getMealAreas(): Result<List<Area>> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult(areaMapper::map)
    }
}
