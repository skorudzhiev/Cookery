package app.cookery.data.daos

import androidx.room.Dao
import androidx.room.Query
import app.cookery.data.CookeryDao
import app.cookery.data.entities.Favorites
import app.cookery.data.entities.categories.CategoryDetails
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
