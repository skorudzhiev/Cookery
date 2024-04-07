package app.cookery.repositories.categories.remote

import app.cookery.TheMealDbApi
import app.cookery.data.Result
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.db.entities.categories.CategoryEntity
import app.cookery.extensions.executeWithRetry
import app.cookery.extensions.toResult
import app.cookery.mappers.area.AreasToArea
import app.cookery.mappers.category.CategoriesToCategory
import app.cookery.mappers.category.MealsToCategoryDetails
import javax.inject.Inject

class CategoriesRemoteDataSourceImpl @Inject constructor(
    private val theMealDbApi: TheMealDbApi,
    private val areaMapper: AreasToArea,
    private val categoryMapper: CategoriesToCategory,
    private val categoryDetailsMapper: MealsToCategoryDetails
) : CategoriesRemoteDataSource {

    override suspend fun getAllMealCategories(): Result<List<CategoryEntity>> {
        return theMealDbApi.getMealCategories()
            .executeWithRetry()
            .toResult(categoryMapper::map)
    }

    override suspend fun getMealsByCategory(category: String): Result<List<CategoryDetailsEntity>> {
        return theMealDbApi.getMealByCategory(category)
            .executeWithRetry()
            .toResult(categoryDetailsMapper::map)
    }

    override suspend fun getMealsByArea(area: String): Result<List<CategoryDetailsEntity>> {
        return theMealDbApi.getMealByArea(area)
            .executeWithRetry()
            .toResult(categoryDetailsMapper::map)
    }

    override suspend fun getMealAreas(): Result<List<AreaEntity>> {
        return theMealDbApi.getMealAreas()
            .executeWithRetry()
            .toResult(areaMapper::map)
    }
}
