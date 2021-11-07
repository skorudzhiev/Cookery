package com.cookery.common.compose.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.cookery.common.compose.theme.getThemePrimaryColor

// Internal padding added by CircularProgressIndicator
private val InternalPadding = 4.dp
// Default stroke size
private val DefaultStrokeWidth = 4.dp
// Preferred diameter for CircularProgressIndicator
private val DefaultDiameter = 40.dp

private val StrokeDiameterFraction = DefaultStrokeWidth / DefaultDiameter

@Composable
fun AutoSizedCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = getThemePrimaryColor()
) {
    BoxWithConstraints(modifier) {
        val diameter = with(LocalDensity.current) {
            // We need to minus the padding added within CircularProgressIndicator
            min(constraints.maxWidth.toDp(), constraints.maxHeight.toDp()) - InternalPadding
        }

        CircularProgressIndicator(
            strokeWidth = (diameter * StrokeDiameterFraction).coerceAtLeast(1.dp),
            color = color
        )
    }
}
