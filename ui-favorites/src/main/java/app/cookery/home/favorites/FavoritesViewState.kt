package app.cookery.home.favorites

import androidx.compose.runtime.Immutable
import app.cookery.data.entities.categories.CategoryDetails
import com.cookery.api.UiError

@Immutable
internal data class FavoritesViewState(
    val favorites: List<CategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: UiError? = null
) {
    companion object {
        val Empty = FavoritesViewState()
    }
}
