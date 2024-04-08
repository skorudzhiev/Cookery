package app.cookery.details.meal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.details.meal.MealDetailsAction.ClearError
import app.cookery.details.meal.MealDetailsAction.UpdateFavoriteMeal
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.cookery.details.CommonPageColumn
import com.cookery.details.ImageMealDetails
import com.cookery.details.MealDetails
import com.cookery.details.SpacerMealDetails
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import com.skorudzhiev.cookery.common.ui.compose.R

@Composable
fun MealDetails(
    navigateUp: () -> Unit
) {
    val viewModel: MealDetailsViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = MealDetailsViewState.Empty)

    MealDetails(
        state = viewState,
        listeners = remember {
            MealDetailsListeners(
                navigateUp = navigateUp,
                updateFavoriteMeal = { viewModel.submitAction(UpdateFavoriteMeal) },
                clearError = { viewModel.submitAction(ClearError) }
            )
        }
    )
}

@Composable
private fun MealDetails(
    state: MealDetailsViewState,
    listeners: MealDetailsListeners
) {
    val scroll = rememberScrollState(0)
    val snackBarHostState = remember { SnackbarHostState() }

    SnackBar(
        errorMessage = state.error,
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
        BackButton(listeners.navigateUp)
        CommonPageColumn(scroll = scroll) {
            MealDetails(
                mealDetails = state.mealDetails,
                onFavoriteButtonPressed = listeners.updateFavoriteMeal,
                isFavoritesButtonSelected = state.isMealMarkedAsFavorite
            )
        }
        ImageMealDetails(
            imageUrl = state.mealDetails?.mealImage,
            scroll = scroll.value
        )
    }
}

@Composable
private fun BackButton(onNavigateUp: () -> Unit) {
    IconButton(
        onClick = onNavigateUp,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.Black,
            contentDescription = stringResource(R.string.cd_navigate_up)
        )
    }
}

private data class MealDetailsListeners(
    val navigateUp: () -> Unit,
    val updateFavoriteMeal: () -> Unit,
    val clearError: () -> Unit
)
