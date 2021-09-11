package com.cookery.common.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable

@Composable
fun CookeryTheme(
    shouldUseDarkColors: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (shouldUseDarkColors) CookeryDarkColors else CookeryLightColors,
        typography = CookeryTypography,
        shapes = Shapes(),
        content = content
    )
}
