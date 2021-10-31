package app.cookery.home.categories

import androidx.compose.runtime.Immutable
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails

@Immutable
internal data class CategoriesViewState(
    val mealCategories: List<Category> = emptyList(),
    val categoriesRefreshing: Boolean = false,
    val areaMeals: List<Area> = emptyList(),
    val areasRefreshing: Boolean = false,
    val popularMeals: List<CategoryDetails> = emptyList(),
    val randomCategoriesRefreshing: Boolean = false,
    val recommendedMeals: List<CategoryDetails> = emptyList(),
    val randomAreasRefreshing: Boolean = false
) {
    val refreshing
        get() = categoriesRefreshing || areasRefreshing || randomCategoriesRefreshing || randomAreasRefreshing

    companion object {
        val Empty = CategoriesViewState()
    }
}
