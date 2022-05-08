package app.cookery.repositories.favorites

import app.cookery.data.daos.FavoritesDao
import app.cookery.data.entities.Favorites
import app.cookery.data.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesStore @Inject constructor(private val dao: FavoritesDao) {

    fun observeFavoriteMeals(): Flow<List<CategoryDetails>> = dao.getFavoriteMeals()

    suspend fun saveFavoriteMeal(favoriteMeal: Favorites) = dao.insert(favoriteMeal)
}
