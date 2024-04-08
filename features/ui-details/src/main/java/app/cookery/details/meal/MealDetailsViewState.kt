package app.cookery.details.meal

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.MealDetails

@Immutable
class MealDetailsViewState(
    val mealDetails: MealDetails? = null,
    val refreshing: Boolean = false,
    val isMealMarkedAsFavorite: Boolean = false,
    val error: String? = null
) {
    companion object {
        val Empty = MealDetailsViewState()
    }
}
