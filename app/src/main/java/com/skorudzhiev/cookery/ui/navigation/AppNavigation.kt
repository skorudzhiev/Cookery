package com.skorudzhiev.cookery.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import app.cookery.home.categories.Categories

object Destinations {
    const val HOME = "home"

    const val CATEGORY_DETAILS = "categoryDetails"
    const val CATEGORY_ARGUMENT = "categoryName"

    const val AREA_DETAILS = "areaDetails"
    const val AREA_ARGUMENT = "areaName"

    const val MEAL_DETAILS = "mealDetails"
    const val MEAL_ARGUMENT = "mealId"
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = Destinations.HOME,
            startDestination = HomeSections.HOME.route
        ) {
            addHomeGraph(navController)
        }
    }
}

fun NavGraphBuilder.addHomeGraph(
    navController: NavController
) {
    addHome(navController)
    addSearch(navController)
    addFavorites(navController)
    addRandom(navController)

    addCategoryDetails(navController)
}

private fun NavGraphBuilder.addHome(
    navController: NavController
) {
    composable(HomeSections.HOME.route) {
        Categories(
            openMealsDetails = { mealId ->
            },
            openCategoryDetails = { categoryName ->
                navController.navigate("${Destinations.CATEGORY_DETAILS}/$categoryName")
            },
            openAreaDetails = { areaName ->
            }
        )
    }
}

private fun NavGraphBuilder.addCategoryDetails(
    navController: NavController
) {
    composable(
        route = "${Destinations.CATEGORY_DETAILS}/{${Destinations.CATEGORY_ARGUMENT}}",
        arguments = listOf(
            navArgument(Destinations.CATEGORY_ARGUMENT) { type = NavType.StringType }
        )
    ) {
        // TODO: Start the composable
    }
}

private fun NavGraphBuilder.addSearch(
    navController: NavController
) {
    composable(HomeSections.SEARCH.route) {
        helloWorld(screen = stringResource(id = HomeSections.SEARCH.title))
    }
}

private fun NavGraphBuilder.addFavorites(
    navController: NavController
) {
    composable(HomeSections.FAVORITES.route) {
        helloWorld(screen = stringResource(id = HomeSections.FAVORITES.title))
    }
}

private fun NavGraphBuilder.addRandom(
    navController: NavController
) {
    composable(HomeSections.RANDOM.route) {
        helloWorld(screen = stringResource(id = HomeSections.RANDOM.title))
    }
}

@Composable
fun helloWorld(screen: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text(text = screen) }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
