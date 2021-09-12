package com.cookery.common.compose.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val WebOrange = Color(255, 168, 0)
private val Silver = Color(203, 203, 203)

val CookeryLightColors = lightColors(
    primary = Color.Black,
    secondary = WebOrange,
    background = Color.White,
    surface = Silver
)

val CookeryDarkColors = darkColors(
    primary = Color.White,
    secondary = WebOrange,
    background = Color.Black,
    surface = Color.Black
)
