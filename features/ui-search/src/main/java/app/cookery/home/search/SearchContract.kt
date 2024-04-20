package app.cookery.home.search

import app.cookery.domain.model.CategoryDetails
import app.cookery.domain.model.MealDetails
import com.cookery.Action
import com.cookery.Effect
import com.cookery.ScreenState

data class SearchScreenState(
    val recentSearches: List<String>,
    val lastOpenedMeals: List<CategoryDetails>,
    val searchResults: List<MealDetails>?,
    val isSearching: Boolean,
    val error: String?
) : ScreenState

sealed class SearchActions : Action {
    data class SearchMealByName(val mealName: String) : SearchActions()
    data object CleanRecentSearchResults : SearchActions()
    data class OpenMealDetails(val mealId: String) : SearchActions()
}

sealed class SearchEffects : Effect {
    data class OpenMealDetails(val mealId: String) : SearchEffects()
}
