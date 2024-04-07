package app.cookery.db.daos

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.FavoritesEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao : CookeryDao<FavoritesEntity> {

    @Query(
        """
            SELECT *
            FROM FavoritesEntity
            LEFT JOIN CategoryDetailsEntity
            ON FavoritesEntity.idMeal = CategoryDetailsEntity.mealId

        """
    )
    fun getFavoriteMeals(): Flow<List<CategoryDetailsEntity>>

    @Query("SELECT * from FavoritesEntity WHERE idMeal = :mealId")
    fun getMealId(mealId: String): Flow<String>
}
