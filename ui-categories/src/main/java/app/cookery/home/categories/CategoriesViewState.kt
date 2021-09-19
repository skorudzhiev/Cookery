package app.cookery.home.categories

import androidx.compose.runtime.Immutable
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas
import app.cookery.data.entities.categories.MealsCollection

@Immutable
internal data class CategoriesViewState(
    val mealsCollection: List<MealsCollection> = emptyList(),
    val collectionRefreshing: Boolean = false,
) {

    val refreshing
        get() = collectionRefreshing

    companion object {
        val Empty = CategoriesViewState()
    }
}
