package app.cookery.db.daos

import androidx.room.Dao
import androidx.room.Query
import app.cookery.db.CookeryDao
import app.cookery.db.entities.Favorites
import app.cookery.db.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao : CookeryDao<Favorites> {

    @Query(
        """
            SELECT *
            FROM Favorites
            LEFT JOIN CategoryDetails
            ON Favorites.idMeal = CategoryDetails.mealId

        """
    )
    fun getFavoriteMeals(): Flow<List<CategoryDetails>>

    @Query("SELECT * from Favorites WHERE idMeal = :mealId")
    fun getMealId(mealId: String): Flow<String>
}
