package app.cookery.details.area

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.AreaWithCategoryDetails

@Immutable
data class AreaDetailsViewState(
    val areaWithCategoryDetails: List<AreaWithCategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: String? = null
) {
    companion object {
        val Empty = AreaDetailsViewState()
    }
}
