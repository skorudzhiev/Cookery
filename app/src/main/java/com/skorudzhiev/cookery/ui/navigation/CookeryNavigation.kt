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
import app.cookery.home.categories.Categories
import com.skorudzhiev.cookery.ui.HomeSections
import com.skorudzhiev.cookery.ui.navigation.MainDestinations.CATEGORY_ID_KEY
import com.skorudzhiev.cookery.ui.navigation.MainDestinations.CATEGORY_TYPE

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val CATEGORY_DETAIL_ROUTE = "route"
    const val CATEGORY_ID_KEY = "categoryId"
    const val CATEGORY_TYPE = "categoryType"
}

fun NavGraphBuilder.addHomeGraph(
    onItemSelected: (String, String, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.CATEGORIES.route) { from ->
        Categories(
            openDetailsScreen = { id, type -> onItemSelected(id, type, from) }
        )
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
                onItemSelected = { selectedId: String, categoryType: String, from: NavBackStackEntry ->
                    if (from.lifecycleIsResumed()) {
                        navController.navigate("${MainDestinations.CATEGORY_DETAIL_ROUTE}/$selectedId/$categoryType")
                    }
                },
                modifier = modifier
            )
        }
        composable(
            "${MainDestinations.CATEGORY_DETAIL_ROUTE}/{$CATEGORY_ID_KEY}/{$CATEGORY_TYPE}",
            arguments = listOf(
                navArgument(CATEGORY_ID_KEY) { type = NavType.StringType },
                navArgument(CATEGORY_TYPE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryId = arguments.getString(CATEGORY_ID_KEY)
            val categoryType = arguments.getString(CATEGORY_TYPE)
            // TODO: RecipeDetails screen composable
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
