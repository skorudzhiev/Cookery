package app.cookery.details.meal

import androidx.compose.runtime.Immutable
import app.cookery.db.entities.MealDetails
import com.cookery.api.UiError

@Immutable
class MealDetailsViewState(
    val mealDetails: MealDetails? = null,
    val refreshing: Boolean = false,
    val isMealMarkedAsFavorite: Boolean = false,
    val error: UiError? = null
) {
    companion object {
        val Empty = MealDetailsViewState()
    }
}
