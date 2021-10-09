package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.Area
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaDao {

    @Query("SELECT * FROM Area")
    fun getMealAreas(): Flow<List<Area>>

    suspend fun insertAreas(areas: List<Area>) {
        areas.forEach {
            insert(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(area: Area)
}
