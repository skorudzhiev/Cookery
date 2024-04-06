package app.cookery.details.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.cookery.common.compose.components.ExpandingText
import com.cookery.common.compose.components.Header
import com.cookery.common.compose.modifiers.Layout

fun LazyListScope.titleItem(
    title: String?,
    showAppBarBackground: Boolean
) = item {
    title?.let { Header(it, showAppBarBackground) }
}

fun LazyListScope.description(description: String?) = description?.let {
    item {
        ExpandingText(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Layout.bodyMargin,
                    vertical = Layout.gutter
                )
        )
    }
}
