package com.skorudzhiev.cookery.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.os.ConfigurationCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cookery.common.compose.theme.CookeryDarkColors
import com.cookery.common.compose.theme.CookeryLightColors
import com.cookery.common.compose.theme.getThemePrimaryColor
import com.google.accompanist.insets.navigationBarsPadding
import com.skorudzhiev.cookery.R

private const val indicatorId = "indicator"
private const val itemNavLayoutIconId = "icon"
private const val itemNavLayoutTextId = "text"
private val bottomNavHeight = 56.dp
private val textIconSpacing = 2.dp
private val bottomNavigationItemPadding = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
private val bottomNavIndicatorShape = RoundedCornerShape(percent = 50)
private val bottomNavLabelTransformOrigin = TransformOrigin(0f, 0.5f)

private const val route_home = "home/categories"
private const val route_search = "home/search"
private const val route_favorites = "home/favorites"
private const val route_random = "home/random"

enum class HomeSections(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    HOME(R.string.home_categories, R.drawable.ic_home, route_home),
    SEARCH(R.string.home_search, R.drawable.ic_search, route_search),
    FAVORITES(R.string.home_favorites, R.drawable.ic_favorite, route_favorites),
    RANDOM(R.string.home_random, R.drawable.ic_dice, route_random)
}

@Composable
fun CookeryBottomNavigationBar(
    navController: NavController,
    tabs: Array<HomeSections>,
    color: Color = bottomNavBarColor(isContent = false),
    contentColor: Color = bottomNavBarColor(isContent = true)
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val sections = remember { HomeSections.values() }
    val routes = remember { sections.map { it.route } }

    if (currentRoute in routes) {
        val currentSection = sections.first { it.route == currentRoute }

        BottomNavigation(
            color = color,
            contentColor = contentColor,
            selectedIndex = currentSection.ordinal,
            itemCount = routes.size,
            tabs = tabs,
            currentSection = currentSection,
            currentRoute = currentRoute,
            navController = navController
        )
    }
}

@Composable
private fun bottomNavBarColor(isContent: Boolean): Color {
    return if (isContent) {
        if (isSystemInDarkTheme()) {
            CookeryDarkColors.primary
        } else {
            CookeryLightColors.primary
        }
    } else {
        if (isSystemInDarkTheme()) {
            CookeryLightColors.primary
        } else {
            CookeryLightColors.background
        }
    }
}

@Composable
fun BottomNavigation(
    color: Color,
    contentColor: Color,
    selectedIndex: Int,
    itemCount: Int,
    tabs: Array<HomeSections>,
    currentSection: HomeSections,
    currentRoute: String?,
    navController: NavController
) {
    Surface(
        color = color,
        contentColor = contentColor
    ) {
        val springSpec = SpringSpec<Float>(
            stiffness = 800f,
            dampingRatio = 0.8f
        )
        BottomNavigationLayout(
            selectedIndex = selectedIndex,
            itemCount = itemCount,
            animSpec = springSpec,
            indicator = { BottomNavIndicator() },
            modifier = Modifier.navigationBarsPadding(start = false, end = false)
        ) {
            tabs.forEach { section ->
                val selected = section == currentSection
                val tint by animateColorAsState(
                    if (selected) {
                        getThemePrimaryColor()
                    } else {
                        getThemePrimaryColor()
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = section.icon),
                            tint = tint,
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(section.title).uppercase(
                                ConfigurationCompat.getLocales(
                                    LocalConfiguration.current
                                ).get(0)
                            ),
                            color = tint,
                            style = MaterialTheme.typography.button,
                            maxLines = 1
                        )
                    },
                    selected = selected,
                    onSelected = {
                        if (section.route != currentRoute) {
                            navController.navigate(section.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(findStartDestination(navController.graph).id) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    animSpec = springSpec,
                    modifier = bottomNavigationItemPadding
                        .clip(bottomNavIndicatorShape)
                )
            }
        }
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

@Composable
private fun BottomNavigationItem(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    selected: Boolean,
    onSelected: () -> Unit,
    animSpec: AnimationSpec<Float>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.selectable(selected = selected, onClick = onSelected),
        contentAlignment = Alignment.Center
    ) {
        // Animate the icon/text positions within the item based on selection
        val animationProgress by animateFloatAsState(if (selected) 1f else 0f, animSpec)
        BottomItemNavLayout(
            icon = icon,
            text = text,
            animationProgress = animationProgress
        )
    }
}

@Composable
private fun BottomItemNavLayout(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
) {
    Layout(
        content = {
            Box(
                modifier = Modifier
                    .layoutId(itemNavLayoutIconId)
                    .padding(horizontal = textIconSpacing),
                content = icon
            )
            val scale = lerp(0.6f, 1f, animationProgress)
            Box(
                modifier = Modifier
                    .layoutId(itemNavLayoutTextId)
                    .padding(horizontal = textIconSpacing)
                    .graphicsLayer {
                        alpha = animationProgress
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = bottomNavLabelTransformOrigin
                    },
                content = text
            )
        },
    ) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.layoutId == itemNavLayoutIconId }.measure(constraints)
        val textPlaceable = measurables.first { it.layoutId == itemNavLayoutTextId }.measure(constraints)

        placeTextAndIcon(
            textPlaceable,
            iconPlaceable,
            constraints.maxWidth,
            constraints.maxHeight,
            animationProgress
        )
    }
}

private fun MeasureScope.placeTextAndIcon(
    textPlaceable: Placeable,
    iconPlaceable: Placeable,
    width: Int,
    height: Int,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
): MeasureResult {
    val iconY = (height - iconPlaceable.height) / 2
    val textY = (height - textPlaceable.height) / 2

    val textWidth = textPlaceable.width * animationProgress
    val iconX = (width - textWidth - iconPlaceable.width) / 2
    val textX = iconX + iconPlaceable.width

    return layout(width, height) {
        iconPlaceable.placeRelative(iconX.toInt(), iconY)
        if (animationProgress != 0f) {
            textPlaceable.placeRelative(textX.toInt(), textY)
        }
    }
}

@Composable
private fun BottomNavIndicator(
    strokeWidth: Dp = 2.dp,
    color: Color = getThemePrimaryColor(),
    shape: Shape = bottomNavIndicatorShape
) {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .then(bottomNavigationItemPadding)
            .border(strokeWidth, color, shape)
    )
}

@Composable
private fun BottomNavigationLayout(
    selectedIndex: Int,
    itemCount: Int,
    animSpec: AnimationSpec<Float>,
    indicator: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Track how "selected" each item is [0, 1]
    val selectionFractions = remember(itemCount) {
        List(itemCount) { i ->
            Animatable(if (i == selectedIndex) 1f else 0f)
        }
    }
    selectionFractions.forEachIndexed { index, selectionFraction ->
        val target = if (index == selectedIndex) 1f else 0f
        LaunchedEffect(target, animSpec) {
            selectionFraction.animateTo(target, animSpec)
        }
    }

    // Animate the indicator position
    val indicatorIndex = remember { Animatable(0f) }
    val targetIndicatorIndex = selectedIndex.toFloat()
    LaunchedEffect(targetIndicatorIndex) {
        indicatorIndex.animateTo(targetIndicatorIndex, animSpec)
    }

    Layout(
        modifier = modifier.height(bottomNavHeight),
        content = {
            content()
            Box(Modifier.layoutId(indicatorId), content = indicator)
        }
    ) { measurables, constraints ->
        check(itemCount == (measurables.size - 1)) // account for indicator

        // Divide the width into n+1 slots and give the selected item 2 slots
        val unselectedWidth = constraints.maxWidth / (itemCount + 1)
        val selectedWidth = 2 * unselectedWidth
        val indicatorMeasurable = measurables.first { it.layoutId == indicatorId }

        val itemPlaceables = measurables
            .filterNot { it == indicatorMeasurable }
            .mapIndexed { index, measurable ->
                // Animate item's width based upon the selection amount
                val width = lerp(unselectedWidth, selectedWidth, selectionFractions[index].value)
                measurable.measure(
                    constraints.copy(
                        minWidth = width,
                        maxWidth = width
                    )
                )
            }
        val indicatorPlaceable = indicatorMeasurable.measure(
            constraints.copy(
                minWidth = selectedWidth,
                maxWidth = selectedWidth
            )
        )

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            val indicatorLeft = indicatorIndex.value * unselectedWidth
            indicatorPlaceable.placeRelative(x = indicatorLeft.toInt(), y = 0)
            var x = 0
            itemPlaceables.forEach { placeable ->
                placeable.placeRelative(x = x, y = 0)
                x += placeable.width
            }
        }
    }
}
