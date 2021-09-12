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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.skorudzhiev.cookery.ui.HomeSections
import com.skorudzhiev.cookery.ui.navigation.MainDestinations.MEAL_ID_KEY

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val MEAL_DETAIL_ROUTE = "meal"
    const val MEAL_ID_KEY = "mealId"
}

fun NavGraphBuilder.addHomeGraph(
    onCategorySelected: (Long, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.CATEGORIES.route) { from ->
        helloWorld(screen = stringResource(id = HomeSections.CATEGORIES.title))
    }
    composable(HomeSections.SEARCH.route) { from ->
        helloWorld(screen = stringResource(id = HomeSections.SEARCH.title))
    }
    composable(HomeSections.FAVORITES.route) { from ->
        helloWorld(screen = stringResource(id = HomeSections.FAVORITES.title))
    }
    composable(HomeSections.RANDOM.route) { from ->
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

@Composable
fun CookeryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.CATEGORIES.route
        ) {
            addHomeGraph(
                onCategorySelected = { mealId: Long, from: NavBackStackEntry ->
                    if (from.lifecycleIsResumed()) {
                        navController.navigate("${MainDestinations.MEAL_DETAIL_ROUTE}/$mealId")
                    }
                },
                modifier = modifier
            )
        }
        composable(
            "${MainDestinations.MEAL_DETAIL_ROUTE}/{$MEAL_ID_KEY}",
            arguments = listOf(navArgument(MEAL_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val recipeId = arguments.getLong(MEAL_ID_KEY)
            // TODO: RecipeDetails screen composable
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
