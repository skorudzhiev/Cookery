package app.cookery.repositories.favorites

import app.cookery.db.daos.FavoritesDao
import app.cookery.db.entities.FavoritesEntity
import app.cookery.db.entities.categories.CategoryDetailsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesLocalDataSource @Inject constructor(private val dao: FavoritesDao) {

    fun observeFavoriteMeals(): Flow<List<CategoryDetailsEntity>> = dao.getFavoriteMeals()

    fun observeFavoriteMeal(mealId: String) = dao.getMealId(mealId)

    suspend fun addFavoriteMeal(favoriteMeal: FavoritesEntity) = dao.insert(favoriteMeal)

    suspend fun removeFavoriteMeal(favoriteMeal: FavoritesEntity) = dao.delete(favoriteMeal)
}
