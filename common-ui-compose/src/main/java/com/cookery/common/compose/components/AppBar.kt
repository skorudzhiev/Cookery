package com.cookery.common.compose.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cookery.common.compose.R
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.drawForegroundGradientScrim
import com.cookery.common.compose.theme.CookeryDarkColors
import com.cookery.common.compose.theme.CookeryLightColors
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun CategoryDetailsAppBar(
    title: String,
    showAppBarBackground: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            showAppBarBackground -> getAppBarColor()
            else -> Color.Transparent
        },
        animationSpec = spring()
    )

    val elevation by animateDpAsState(
        targetValue = when {
            showAppBarBackground -> 4.dp
            else -> 0.dp
        },
        animationSpec = spring(),
    )

    TopAppBar(
        title = {
            Crossfade(showAppBarBackground) { show ->
                if (show) Text(title)
            }
        },
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.systemBars,
            applyBottom = false
        ),
        navigationIcon = {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.cd_navigate_up)
                )
            }
        },
        elevation = elevation,
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}

@Composable
fun BackdropImage(
    backdropImage: String,
    listState: LazyListState
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 10)
            .clipToBounds()
            .offset {
                IntOffset(
                    x = 0,
                    y = if (listState.firstVisibleItemIndex == 0) {
                        listState.firstVisibleItemScrollOffset / 2
                    } else 0
                )
            }
    ) {
        Box {
            Image(
                painter = rememberImagePainter(backdropImage) { crossfade(true) },
                contentDescription = stringResource(id = R.string.cd_category_backdrop),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .drawForegroundGradientScrim(Color.Black.copy(alpha = 0.7f))
            )
        }
    }
}

@Composable
fun Header(
    title: String,
    showAppBarBackground: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Layout.bodyMargin, vertical = Layout.gutter)
    ) {
        val originalTextStyle = MaterialTheme.typography.h5

        Crossfade(!showAppBarBackground) { show ->
            if (show) Text(
                text = title,
                style = originalTextStyle.copy(
                    color = CookeryDarkColors.secondary,
                ),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun getAppBarColor(): Color {
    return if (isSystemInDarkTheme()) {
        CookeryDarkColors.background
    } else {
        CookeryLightColors.background
    }
}
