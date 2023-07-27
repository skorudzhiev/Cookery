package com.cookery.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cookery.common.compose.theme.CookeryLightColors
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun SpacerMealDetails() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(CookeryLightColors.background)
    )
}

@Composable
fun SpacerPageContent() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(minTitleOffset)
    )
}
