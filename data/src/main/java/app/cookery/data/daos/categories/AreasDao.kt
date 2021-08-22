package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.Areas
import kotlinx.coroutines.flow.Flow

@Dao
interface AreasDao {

    @Query("SELECT *, `areas` FROM areas")
    fun getMealAreas(): Flow<Areas>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(areas: Areas)
}
