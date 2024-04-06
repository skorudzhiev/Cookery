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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.cookery.details.area.AreaDetails
import app.cookery.details.category.CategoryDetails
import app.cookery.details.meal.MealDetails
import app.cookery.home.categories.Categories
import app.cookery.home.favorites.Favorites
import app.cookery.home.random.RandomMeal

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
    addRandom()

    addCategoryDetails(navController)
    addAreaDetails(navController)
    addMealDetails(navController)
}

private fun NavGraphBuilder.addHome(
    navController: NavController
) {
    composable(HomeSections.HOME.route) {
        Categories(
            openMealsDetails = { mealId ->
                navController.navigate("${Destinations.MEAL_DETAILS}/$mealId")
            },
            openCategoryDetails = { categoryName ->
                navController.navigate("${Destinations.CATEGORY_DETAILS}/$categoryName")
            },
            openAreaDetails = { areaName ->
                navController.navigate("${Destinations.AREA_DETAILS}/$areaName")
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
        CategoryDetails(
            navigateUp = navController::navigateUp,
            openMealDetails = { mealId ->
                navController.navigate("${Destinations.MEAL_DETAILS}/$mealId")
            }
        )
    }
}

private fun NavGraphBuilder.addAreaDetails(
    navController: NavController
) {
    composable(
        route = "${Destinations.AREA_DETAILS}/{${Destinations.AREA_ARGUMENT}}",
        arguments = listOf(
            navArgument(Destinations.AREA_ARGUMENT) { type = NavType.StringType }
        )
    ) {
        AreaDetails(
            navigateUp = navController::navigateUp,
            openMealDetails = { mealId ->
                navController.navigate("${Destinations.MEAL_DETAILS}/$mealId")
            }
        )
    }
}
private fun NavGraphBuilder.addMealDetails(
    navController: NavController
) {
    composable(
        route = "${Destinations.MEAL_DETAILS}/{${Destinations.MEAL_ARGUMENT}}",
        arguments = listOf(
            navArgument(Destinations.MEAL_ARGUMENT) { type = NavType.StringType }
        )
    ) {
        MealDetails(
            navigateUp = navController::navigateUp
        )
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
        Favorites(
            openMealDetails = { mealId ->
                navController.navigate("${Destinations.MEAL_DETAILS}/$mealId")
            }
        )
    }
}

private fun NavGraphBuilder.addRandom() = composable(HomeSections.RANDOM.route) { RandomMeal() }

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
