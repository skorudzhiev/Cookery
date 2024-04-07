package app.cookery.repositories.areas

import app.cookery.domain.repositories.AreaRepository
import app.cookery.mappers.area.AreaMapper
import app.cookery.mappers.category.CategoryDetailsMapper
import app.cookery.repositories.categories.CategoriesLocalDataSource
import app.cookery.repositories.categories.remote.CategoriesRemoteDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaRepositoryImpl @Inject constructor(
    private val dataSource: CategoriesRemoteDataSource,
    private val store: AreaLocalDataSource,
    private val categoriesLocalDataSource: CategoriesLocalDataSource,
    private val categoryDetailsMapper: CategoryDetailsMapper,
    private val areaMapper: AreaMapper
) : AreaRepository {

    override fun observeRandomAreaMeals() = store.observeRandomAreaMeals()
        .map { list -> list.map { categoryDetailsMapper.map(it) } }

    override fun observeAreaWithCategoryDetails(areaName: String) =
        store.observeAreaWithCategoryDetails(areaName)

    override fun observeAreaMeals() = store.observeAreaMeals()
        .map { list -> list.map { areaMapper.map(it) } }

    override suspend fun fetchAllMealAreas() {
        val response = dataSource.getMealAreas().getOrThrow()
        store.saveAreaMeals(response)
    }

    override suspend fun fetchMealsByArea(area: String) {
        val response = dataSource.getMealsByArea(area).getOrThrow()
        categoriesLocalDataSource.saveCategoryDetails(
            meals = response,
            categoryName = "",
            area = area
        )
    }
}
