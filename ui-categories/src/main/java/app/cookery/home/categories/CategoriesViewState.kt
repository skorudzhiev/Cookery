package app.cookery.home.categories

import androidx.compose.runtime.Immutable
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Areas

@Immutable
internal data class CategoriesViewState(
    val allMealCategories: AllMealCategories = AllMealCategories(emptyList()),
    val categoriesRefreshing: Boolean = false,
    val allMealAreas: Areas = Areas(emptyList()),
    val areasRefreshing: Boolean = false
) {

    val refreshing
        get() = categoriesRefreshing || areasRefreshing

    companion object {
        val Empty = CategoriesViewState()
    }
}
