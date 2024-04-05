package app.cookery.repositories.categories.remote

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.AreasToArea
import app.cookery.mappers.CategoriesToCategory
import app.cookery.mappers.MealsToCategoryDetails
import javax.inject.Inject

class CategoriesRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val areaMapper: AreasToArea,
    private val categoryMapper: CategoriesToCategory,
    private val categoryDetailsMapper: MealsToCategoryDetails
) : CategoriesRemoteDataSource {

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

    override suspend fun getMealsByArea(area: String): Result<List<CategoryDetails>> {
        return theMealDbApi.getMealByArea(area)
            .executeWithRetry()
            .toResult(categoryDetailsMapper::map)
    }

    override suspend fun getMealAreas(): Result<List<Area>> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult(areaMapper::map)
    }
}
