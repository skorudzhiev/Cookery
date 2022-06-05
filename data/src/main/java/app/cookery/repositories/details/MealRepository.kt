package app.cookery.repositories.details

import app.cookery.data.entities.Favorites
import app.cookery.repositories.favorites.FavoritesStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(
    private val dataSource: MealDataSource,
    private val mealStore: MealStore,
    private val favoritesStore: FavoritesStore
) {

    fun observeMealDetails(mealId: String) = mealStore.observeMeal(mealId)

    suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        mealStore.saveMeal(response)
    }

    fun isMealMarkedAsFavorite(mealId: String): Flow<String> = favoritesStore.observeFavoriteMeal(mealId)

    suspend fun updateFavoriteMeal(mealId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoritesStore.removeFavoriteMeal(favoriteMeal = Favorites(mealId = mealId))
            return
        }
        favoritesStore.addFavoriteMeal(favoriteMeal = Favorites(mealId = mealId))
    }
}
