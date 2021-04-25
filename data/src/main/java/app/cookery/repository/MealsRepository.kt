package app.cookery.repository

import app.cookery.data.Success
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealsRepository @Inject constructor(
    private val mealsDataSource: MealsDataSource
) {

    suspend fun getMealsCategories() = coroutineScope {
        val result = async {
            mealsDataSource.getCategories()
        }

        val meals = result.await().let {
            if (it is Success) it.data else null
        }
    }
}
