package com.cookery.common.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cookery.common.compose.theme.CookeryDarkColors
import com.cookery.common.compose.theme.CookeryLightColors
import com.cookery.common.compose.theme.getThemeColorForImageBorder

@Composable
fun CardItemHome(
    categoryType: String?,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {

    CookeryCard(
        modifier = modifier
            .size(
                width = 80.dp,
                height = 140.dp
            )
            .offset(y = 40.dp)
            .padding(bottom = 40.dp)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categoryType?.let {
                Text(
                    text = it,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,
                    modifier = modifier
                        .padding(top = 50.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }

    DrawCircle(
        color = getThemeColorForImageBorder(),
    )

    BorderlessCircularImage(
        imageUrl = imageUrl,
        modifier = modifier
    )
}

@Composable
private fun DrawCircle(
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(3.dp, getThemeColorForCardHead()),
    ) {
        Canvas(
            modifier = modifier
                .size(80.dp),
            onDraw = {
                drawCircle(color = color)
            }
        )
    }
}

@Composable
private fun getThemeColorForCardHead(): Color {
    return if (isSystemInDarkTheme()) {
        CookeryLightColors.background
    } else {
        CookeryLightColors.surface
    }
}

@Composable
private fun BorderlessCircularImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Surface(
            color = Color.LightGray,
            shape = CircleShape,
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(
                    top = 8.dp,
                    start = 8.dp
                )
        ) {
            imageUrl?.let {
                Image(
                    painter = rememberImagePainter(
                        data = it,
                        builder = { crossfade(true) }
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .size(64.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun CookeryCard(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = RoundedCornerShape(corner = CornerSize(15.dp)),
    color: Color = getThemeCardColor(),
    contentColor: Color = Color.Black,
    border: BorderStroke? = null,
    elevation: Dp = 5.dp,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        content = content
    )
}

@Composable
private fun getThemeCardColor(): Color {
    return if (isSystemInDarkTheme()) {
        CookeryDarkColors.primary
    } else {
        CookeryLightColors.background
    }
}
