package app.cookery.details.category

import androidx.compose.runtime.Immutable
import app.cookery.db.entities.relations.CategoryWithCategoryDetails
import com.cookery.api.UiError

@Immutable
data class CategoryDetailsViewState(
    val categoryWithCategoryDetails: List<CategoryWithCategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: UiError? = null
) {
    companion object {
        val Empty = CategoryDetailsViewState()
    }
}
