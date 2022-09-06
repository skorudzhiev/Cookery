package app.cookery.repositories.categories.areas

import app.cookery.data.daos.categories.AreaDao
import app.cookery.data.daos.categories.CategoryDetailsDao
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.CategoryDetails
import app.cookery.data.entities.relations.AreaWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AreaStore @Inject constructor(
    private val areaDao: AreaDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomAreaMeals(): Flow<List<CategoryDetails>> = categoryDetailsDao.getRandomAreaMeals()

    fun observeAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>> =
        areaDao.getAreaWithCategoryDetails(areaName)

    fun observeAreaMeals(): Flow<List<Area>> = areaDao.getMealAreas()

    suspend fun saveAreaMeals(areas: List<Area>) = areaDao.insertDetails(areas)
}
