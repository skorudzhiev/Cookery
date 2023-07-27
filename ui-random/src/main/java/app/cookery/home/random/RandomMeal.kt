package app.cookery.home.random

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.ClearError
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealAction.UpdateFavoriteMeal
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealViewState
import app.cookery.home.random.RandomMealViewModel.Companion.RandomMealViewState.Companion.Empty
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.cookery.details.CommonPageColumn
import com.cookery.details.ImageMealDetails
import com.cookery.details.MealDetails
import com.cookery.details.SpacerMealDetails
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun RandomMeal() {

    val lifecycleEvent = rememberLifecycleEvent()
    val viewModel: RandomMealViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = Empty)

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.retrieveRandomMeal()
        }
    }

    RandomMeal(
        viewState = viewState,
        listeners = remember {
            RandomMealListeners(
                updateFavoriteMeal = { viewModel.submitAction(UpdateFavoriteMeal) },
                clearError = { viewModel.submitAction(ClearError) }
            )
        }
    )
}

@Composable
private fun RandomMeal(
    viewState: RandomMealViewState,
    listeners: RandomMealListeners
) {

    val scroll = rememberScrollState(0)
    val snackBarHostState = remember { SnackbarHostState() }

    SnackBar(
        error = viewState.error,
        snackBarHostState = snackBarHostState,
        onClearError = listeners.clearError
    )

    Box(
        Modifier
            .fillMaxSize()
            .padding(
                rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    applyBottom = true,
                    applyTop = false,
                )
            )
    ) {
        SpacerMealDetails()
        CommonPageColumn(scroll = scroll) {
            MealDetails(
                mealDetails = viewState.randomMeal,
                onFavoriteButtonPressed = listeners.updateFavoriteMeal,
                isFavoritesButtonSelected = viewState.isMealMarkedAsFavorite
            )
        }
        ImageMealDetails(
            imageUrl = viewState.randomMeal?.mealImage,
            scroll = scroll.value
        )
    }
}

@Composable
private fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}

private data class RandomMealListeners(
    val updateFavoriteMeal: () -> Unit,
    val clearError: () -> Unit
)
