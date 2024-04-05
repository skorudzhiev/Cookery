package app.cookery.repositories.favorites

import app.cookery.db.daos.FavoritesDao
import app.cookery.db.entities.Favorites
import app.cookery.db.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesLocalDataSource @Inject constructor(private val dao: FavoritesDao) {

    fun observeFavoriteMeals(): Flow<List<CategoryDetails>> = dao.getFavoriteMeals()

    fun observeFavoriteMeal(mealId: String) = dao.getMealId(mealId)

    suspend fun addFavoriteMeal(favoriteMeal: Favorites) = dao.insert(favoriteMeal)

    suspend fun removeFavoriteMeal(favoriteMeal: Favorites) = dao.delete(favoriteMeal)
}
