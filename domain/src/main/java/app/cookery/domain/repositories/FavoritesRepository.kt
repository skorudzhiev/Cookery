package app.cookery.domain.repositories

import app.cookery.domain.model.CategoryDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun observeFavoriteMeals(): Flow<List<CategoryDetails>>
}
