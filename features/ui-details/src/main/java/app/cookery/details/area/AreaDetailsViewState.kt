package app.cookery.details.area

import androidx.compose.runtime.Immutable
import app.cookery.domain.model.AreaWithCategoryDetails
import com.cookery.api.UiError

@Immutable
data class AreaDetailsViewState(
    val areaWithCategoryDetails: List<AreaWithCategoryDetails> = emptyList(),
    val refreshing: Boolean = false,
    val error: UiError? = null
) {
    companion object {
        val Empty = AreaDetailsViewState()
    }
}
