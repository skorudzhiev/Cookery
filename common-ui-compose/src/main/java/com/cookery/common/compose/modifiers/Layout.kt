package com.cookery.common.compose.modifiers

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

object Layout {

    val bodyMargin: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 16.dp
            in 600..904 -> 32.dp
            in 905..1239 -> 0.dp
            in 1240..1439 -> 200.dp
            else -> 0.dp
        }

    val gutter: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 8.dp
            in 600..904 -> 16.dp
            in 905..1239 -> 16.dp
            in 1240..1439 -> 32.dp
            else -> 32.dp
        }

    val bodyMaxWidth: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> Dp.Infinity
            in 600..904 -> Dp.Infinity
            in 905..1239 -> 840.dp
            in 1240..1439 -> Dp.Infinity
            else -> 1040.dp
        }

    val columns: Int
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 4
            in 600..904 -> 8
            else -> 12
        }
}

fun Modifier.bodyWidth() = fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .composed {
        val bodyMaxWidth = Layout.bodyMaxWidth
        if (bodyMaxWidth.isFinite) widthIn(max = bodyMaxWidth) else this
    }
    .composed {
        padding(
            rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyBottom = false,
                applyTop = false,
            )
        )
    }

fun Modifier.iconButtonBackgroundScrim(
    enabled: Boolean = true,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 0.4f,
    shape: Shape = CircleShape,
): Modifier = composed {
    if (enabled) {
        Modifier.background(
            color = MaterialTheme.colors.surface.copy(alpha = alpha),
            shape = shape,
        )
    } else this
}

@Composable
fun PaddingValues.copy(
    copyStart: Boolean = true,
    copyTop: Boolean = true,
    copyEnd: Boolean = true,
    copyBottom: Boolean = true,
): PaddingValues {
    return remember(this) {
        derivedStateOf {
            PaddingValues(
                start = if (copyStart) calculateStartPadding(LayoutDirection.Ltr) else 0.dp,
                top = if (copyTop) calculateTopPadding() else 0.dp,
                end = if (copyEnd) calculateEndPadding(LayoutDirection.Ltr) else 0.dp,
                bottom = if (copyBottom) calculateBottomPadding() else 0.dp,
            )
        }
    }.value
}
