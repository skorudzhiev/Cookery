package app.cookery.details

import androidx.compose.runtime.Immutable
import app.cookery.data.entities.relations.CategoryWithCategoryDetails

@Immutable
data class CategoryDetailsViewState(
    val categoryWithCategoryDetails: List<CategoryWithCategoryDetails> = emptyList(),
    val refreshing: Boolean = false
) {
    companion object {
        val Empty = CategoryDetailsViewState()
    }
}
