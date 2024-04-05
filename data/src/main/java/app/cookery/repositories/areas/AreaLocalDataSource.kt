package app.cookery.repositories.areas

import app.cookery.db.daos.categories.AreaDao
import app.cookery.db.daos.categories.CategoryDetailsDao
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.CategoryDetails
import app.cookery.db.entities.relations.AreaWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AreaLocalDataSource @Inject constructor(
    private val areaDao: AreaDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomAreaMeals(): Flow<List<CategoryDetails>> = categoryDetailsDao.getRandomAreaMeals()

    fun observeAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>> =
        areaDao.getAreaWithCategoryDetails(areaName)

    fun observeAreaMeals(): Flow<List<Area>> = areaDao.getMealAreas()

    suspend fun saveAreaMeals(areas: List<Area>) = areaDao.insertDetails(areas)
}
