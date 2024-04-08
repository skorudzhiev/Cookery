package app.cookery.details.category

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.CategoryWithCategoryDetails

@Immutable
data class CategoryDetailsViewState(
    val categoryWithCategoryDetails: List<CategoryWithCategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: String? = null
) {
    companion object {
        val Empty = CategoryDetailsViewState()
    }
}
