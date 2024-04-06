package app.cookery.details.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.max
import com.cookery.common.compose.modifiers.Layout

fun LazyListScope.spacer() {
    item {
        Spacer(
            modifier = Modifier.height(
                max(
                    a = Layout.gutter,
                    b = Layout.bodyMargin
                )
            )
        )
    }
}
