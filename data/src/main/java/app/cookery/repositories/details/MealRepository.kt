package app.cookery.repositories.details

import app.cookery.db.entities.Favorites
import app.cookery.repositories.details.remote.MealRemoteDataSource
import app.cookery.repositories.favorites.FavoritesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(
    private val dataSource: MealRemoteDataSource,
    private val mealLocalDataSource: MealLocalDataSource,
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) {

    fun observeMealDetails(mealId: String) = mealLocalDataSource.observeMeal(mealId)

    suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        mealLocalDataSource.saveMeal(response)
    }

    fun isMealMarkedAsFavorite(mealId: String): Flow<String> = favoritesLocalDataSource.observeFavoriteMeal(mealId)

    suspend fun updateFavoriteMeal(mealId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoritesLocalDataSource.removeFavoriteMeal(favoriteMeal = Favorites(mealId = mealId))
            return
        }
        favoritesLocalDataSource.addFavoriteMeal(favoriteMeal = Favorites(mealId = mealId))
    }
}
