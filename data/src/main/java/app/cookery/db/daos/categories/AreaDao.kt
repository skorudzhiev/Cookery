package app.cookery.db.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.categories.AreaEntity
import app.cookery.domain.model.AreaWithCategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaDao : CookeryDao<AreaEntity> {

    @Query("SELECT * FROM AreaEntity")
    fun getMealAreas(): Flow<List<AreaEntity>>

    @Query(
        """
            SELECT CategoryDetailsEntity.mealId, mealName, mealImage, AreaEntity.area
            FROM AreaEntity
            INNER JOIN CategoryDetailsEntity ON AreaEntity.area = CategoryDetailsEntity.area
            WHERE AreaEntity.area = :areaName
        """
    )
    fun getAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>>
}
