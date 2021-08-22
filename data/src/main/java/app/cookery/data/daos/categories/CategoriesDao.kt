package app.cookery.data.daos.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.cookery.data.entities.categories.AllMealCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT *, `categories` FROM categories")
    fun getAllMealCategories(): Flow<AllMealCategories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allMealCategories: AllMealCategories)
}
