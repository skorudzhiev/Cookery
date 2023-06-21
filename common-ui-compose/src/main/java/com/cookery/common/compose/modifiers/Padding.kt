package com.cookery.common.compose.modifiers

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun pageDetailsPadding() = rememberInsetsPaddingValues(
    insets = LocalWindowInsets.current.systemBars,
    applyBottom = true,
    applyTop = false,
)
