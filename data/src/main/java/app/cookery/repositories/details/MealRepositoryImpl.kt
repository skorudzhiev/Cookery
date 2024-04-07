package app.cookery.repositories.details

import app.cookery.db.entities.FavoritesEntity
import app.cookery.domain.repositories.MealRepository
import app.cookery.mappers.meal.MealDetailsMapper
import app.cookery.repositories.details.remote.MealRemoteDataSource
import app.cookery.repositories.favorites.FavoritesLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val dataSource: MealRemoteDataSource,
    private val mealLocalDataSource: MealLocalDataSource,
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val mealDetailsMapper: MealDetailsMapper
) : MealRepository {

    override fun observeMealDetails(mealId: String) = mealLocalDataSource.observeMeal(mealId)
        .map { mealDetailsMapper.map(it) }

    override suspend fun fetchMealDetails(mealId: String) {
        val response = dataSource.getMealDetails(mealId).getOrThrow()
        mealLocalDataSource.saveMeal(response)
    }

    override fun isMealMarkedAsFavorite(mealId: String): Flow<String> = favoritesLocalDataSource.observeFavoriteMeal(mealId)

    override suspend fun updateFavoriteMeal(mealId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoritesLocalDataSource.removeFavoriteMeal(favoriteMeal = FavoritesEntity(mealId = mealId))
            return
        }
        favoritesLocalDataSource.addFavoriteMeal(favoriteMeal = FavoritesEntity(mealId = mealId))
    }
}
