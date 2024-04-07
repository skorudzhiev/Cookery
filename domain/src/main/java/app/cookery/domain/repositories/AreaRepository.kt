package app.cookery.domain.repositories

import app.cookery.domain.model.Area
import app.cookery.domain.model.AreaWithCategoryDetails
import app.cookery.domain.model.CategoryDetails
import kotlinx.coroutines.flow.Flow

interface AreaRepository {

    fun observeRandomAreaMeals(): Flow<List<CategoryDetails>>
    fun observeAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>>
    fun observeAreaMeals(): Flow<List<Area>>

    suspend fun fetchAllMealAreas()
    suspend fun fetchMealsByArea(area: String)
}
