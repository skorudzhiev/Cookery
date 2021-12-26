package com.cookery.common.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val youTubeButtonColor = Color(240, 58, 58)
private val WebOrange = Color(255, 168, 0)
private val Silver = Color(203, 203, 203)

val CookeryLightColors = lightColors(
    primary = Color.Black,
    secondary = WebOrange,
    background = Silver,
    surface = Color.White
)

val CookeryDarkColors = darkColors(
    primary = Color.White,
    secondary = WebOrange,
    background = Color.Black,
    surface = Color.Black
)

@Composable
fun getThemePrimaryColor(): Color {
    return if (isSystemInDarkTheme()) {
        CookeryDarkColors.primary
    } else {
        CookeryLightColors.primary
    }
}

@Composable
fun getThemeColorForImageBorder(): Color {
    return if (isSystemInDarkTheme()) {
        CookeryDarkColors.primary
    } else {
        CookeryLightColors.background
    }
}
