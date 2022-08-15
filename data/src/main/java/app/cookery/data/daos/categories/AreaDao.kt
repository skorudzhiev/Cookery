package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.CookeryDao
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.relations.AreaWithCategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaDao : CookeryDao<Area> {

    @Query("SELECT * FROM Area")
    fun getMealAreas(): Flow<List<Area>>

    @Query(
        """
            SELECT CategoryDetails.mealId, mealName, mealImage, Area.area
            FROM Area
            INNER JOIN CategoryDetails ON Area.area = CategoryDetails.area
            WHERE Area.area = :areaName
        """
    )
    fun getAreaWithCategoryDetails(areaName: String): Flow<List<AreaWithCategoryDetails>>
}
