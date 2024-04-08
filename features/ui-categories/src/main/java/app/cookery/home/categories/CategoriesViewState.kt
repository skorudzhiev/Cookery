package app.cookery.home.categories

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.Area
import app.cookery.domain.model.Category
import app.cookery.domain.model.CategoryDetails

@Immutable
internal data class CategoriesViewState(
    val mealCategories: List<Category> = emptyList(),
    val categoriesRefreshing: Boolean = false,
    val areaMeals: List<Area> = emptyList(),
    val areasRefreshing: Boolean = false,
    val popularMeals: List<CategoryDetails> = emptyList(),
    val randomCategoriesRefreshing: Boolean = false,
    val recommendedMeals: List<CategoryDetails> = emptyList(),
    val randomAreasRefreshing: Boolean = false,
    val error: String? = null
) {
    val refreshing
        get() = categoriesRefreshing || areasRefreshing || randomCategoriesRefreshing || randomAreasRefreshing

    companion object {
        val Empty = CategoriesViewState()
    }
}
