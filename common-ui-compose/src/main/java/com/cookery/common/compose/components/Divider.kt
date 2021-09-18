package com.cookery.common.compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cookery.common.compose.theme.getThemePrimaryColor

private const val DividerAlpha = 0.12f

@Composable
fun CookeryDivider(
    modifier: Modifier = Modifier,
    color: Color = getThemePrimaryColor().copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp
            ),
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}
