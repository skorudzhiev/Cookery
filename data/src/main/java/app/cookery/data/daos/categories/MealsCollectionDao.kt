package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.MealsCollection
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsCollectionDao {

    @Query("SELECT * FROM meals_collection")
    fun getMealsCollection(): Flow<List<MealsCollection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealsCollection: MealsCollection)
}