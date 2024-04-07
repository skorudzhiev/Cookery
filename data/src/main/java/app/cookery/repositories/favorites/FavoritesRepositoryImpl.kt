package app.cookery.repositories.favorites

import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.repositories.FavoritesRepository
import app.cookery.mappers.category.CategoryDetailsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val store: FavoritesLocalDataSource,
    private val mapper: CategoryDetailsMapper
) : FavoritesRepository {

    override fun observeFavoriteMeals(): Flow<List<CategoryDetails>> = store.observeFavoriteMeals()
        .map { list -> list.map { mapper.map(it) } }
}
