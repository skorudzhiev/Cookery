package app.cookery.repositories.categories

import app.cookery.domain.repositories.CategoriesRepository
import app.cookery.mappers.category.CategoryDetailsMapper
import app.cookery.mappers.category.CategoryMapper
import app.cookery.repositories.categories.remote.CategoriesRemoteDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    private val dataSource: CategoriesRemoteDataSource,
    private val store: CategoriesLocalDataSource,
    private val categoryDetailsMapper: CategoryDetailsMapper,
    private val categoryMapper: CategoryMapper,
) : CategoriesRepository {

    override fun observeRandomCategoryDetails() = store.observeRandomCategoryMeals()
        .map { list -> list.map { categoryDetailsMapper.map(it) } }

    override fun observeAllMealCategories() = store.observeAllMealCategories()
        .map { list -> list.map { categoryMapper.map(it) } }

    override fun observeCategoryWithCategoryDetails(categoryName: String) =
        store.observeCategoryWithCategoryDetails(categoryName)

    override suspend fun fetchAllMealCategories() {
        val response = dataSource.getAllMealCategories().getOrThrow()
        store.saveAllMealCategories(response)
    }

    override suspend fun fetchMealsByCategory(categoryName: String) {
        val response = dataSource.getMealsByCategory(categoryName).getOrThrow()
        store.saveCategoryDetails(
            meals = response,
            categoryName = categoryName,
            area = ""
        )
    }
}
