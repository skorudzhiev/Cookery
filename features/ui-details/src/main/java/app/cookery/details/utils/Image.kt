package app.cookery.details.utils

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import com.cookery.common.compose.components.BackdropImage

fun LazyListScope.backDropImage(
    backdrop: String?,
    listState: LazyListState
) {
    item {
        backdrop?.let {
            BackdropImage(
                backdropImage = it,
                listState = listState
            )
        }
    }
}
