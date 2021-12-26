package com.cookery.common.compose.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cookery.common.compose.theme.getThemePrimaryColor

@Composable
fun DrawCircle(
    paddingStart: Dp,
    size: Float,
    color: Color = getThemePrimaryColor()
) {
    Canvas(
        modifier = Modifier
            .padding(start = paddingStart)
    ) {
        drawCircle(
            color = color,
            radius = size
        )
    }
}
