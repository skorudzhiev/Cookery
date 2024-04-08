package app.cookery.home.favorites

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.CategoryDetails

@Immutable
internal data class FavoritesViewState(
    val favorites: List<CategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: String? = null
) {
    companion object {
        val Empty = FavoritesViewState()
    }
}
