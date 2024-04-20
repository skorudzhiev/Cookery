package app.cookery.home.search

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cookery.home.search.SearchEffects.OpenMealDetails
import com.cookery.common.compose.HandleEffects
import com.cookery.common.compose.components.SnackBar

@Composable
fun SearchScreen(
    openMealsDetails: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val viewState = viewModel.state.collectAsStateWithLifecycle().value

    HandleEffects(effectFlow = viewModel.effect) { effect ->
        when (effect) {
            is OpenMealDetails -> openMealsDetails(effect.mealId)
        }
    }

    SearchScreenLayout(
        state = viewState,
        onActionSent = viewModel::sendAction
    )
}

@Composable
private fun SearchScreenLayout(
    state: SearchState,
    onActionSent: (SearchActions) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    SnackBar(
        onActionSent = onActionSent,
        snackbarHostState = snackbarHostState,
        error = state.error
    )

    // TODO: Display Search Bar
    // TODO: Display Recent Searches
    // TODO: Display Last Opened Meals
}

@Composable
private fun SnackBar(
    onActionSent: (SearchActions) -> Unit,
    snackbarHostState: SnackbarHostState,
    error: String?,
) {
    SnackBar(
        onClearError = { onActionSent(SearchActions.CleanRecentSearchResults) },
        snackBarHostState = snackbarHostState,
        errorMessage = error
    )
    LaunchedEffect(error) {
        error?.let { error ->
            snackbarHostState.showSnackbar(error)
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreenLayout(
        state = SearchState(
            recentSearches = emptyList(),
            lastOpenedMeals = emptyList(),
            searchResults = emptyList(),
            isLoading = false,
            error = null
        )
    ) {
        // no-op
    }
}
