package com.skorudzhiev.cookery.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cookery.common.compose.theme.CookeryDarkColors
import com.cookery.common.compose.theme.CookeryLightColors
import com.skorudzhiev.cookery.R

private const val route_categories = "home/categories"
private const val route_search = "home/search"
private const val route_favorites = "home/favorites"
private const val route_random = "home/random"

enum class HomeSections(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    CATEGORIES(R.string.home_categories, R.drawable.ic_home, route_categories),
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
