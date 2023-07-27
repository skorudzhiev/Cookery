package com.cookery.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import com.cookery.common.compose.components.CircularBorderImage
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

val minTitleOffset = 56.dp
val gradientScroll = 180.dp
val imageOverlap = 115.dp

private val minImageOffset = 12.dp
private val maxTitleOffset = imageOverlap + minTitleOffset + gradientScroll
private val expandedImageSize = 300.dp
private val collapsedImageSize = 150.dp
private val hZPadding = Modifier.padding(horizontal = 24.dp)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageMealDetails(
    imageUrl: String?,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (maxTitleOffset - minTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = hZPadding.then(Modifier.statusBarsPadding())
    ) {
        CircularBorderImage(
            imageUrl = imageUrl.toString(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            borderStrokeSize = 1.dp,
            borderStrokeColor = Color.White,
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(expandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(collapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(minTitleOffset, minImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}
