package app.cookery.repositories.areas

import app.cookery.db.daos.categories.AreaDao
import app.cookery.db.daos.categories.CategoryDetailsDao
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import app.cookery.domain.model.AreaWithCategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AreaLocalDataSource @Inject constructor(
    private val areaDao: AreaDao,
    private val categoryDetailsDao: CategoryDetailsDao
) {

    fun observeRandomAreaMeals(): Flow<List<CategoryDetailsEntity>> = categoryDetailsDao.getRandomAreaMeals()

    fun observeAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>> =
        areaDao.getAreaWithCategoryDetails(areaName)

    fun observeAreaMeals(): Flow<List<AreaEntity>> = areaDao.getMealAreas()

    suspend fun saveAreaMeals(areaEntities: List<AreaEntity>) = areaDao.insertDetails(areaEntities)
}
