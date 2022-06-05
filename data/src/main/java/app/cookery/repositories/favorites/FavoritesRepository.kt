package app.cookery.repositories.favorites

import app.cookery.data.entities.categories.CategoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(private val store: FavoritesStore) {

    fun observeFavoriteMeals(): Flow<List<CategoryDetails>> = store.observeFavoriteMeals()
}
