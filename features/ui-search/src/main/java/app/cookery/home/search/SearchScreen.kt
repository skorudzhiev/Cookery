package app.cookery.home.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cookery.home.search.SearchActions.ClearError
import app.cookery.home.search.SearchActions.ClearSearchQuery
import app.cookery.home.search.SearchActions.SearchMealByName
import app.cookery.home.search.SearchEffects.OpenMealDetails
import app.cookery.home.search.SearchScreenContentType.Placeholder
import app.cookery.home.search.SearchScreenContentType.Recent
import app.cookery.home.search.SearchScreenContentType.SearchResults
import app.cookery.home.search.components.LastOpenedMeals
import app.cookery.home.search.components.RecentSearches
import app.cookery.home.search.components.SearchBar
import app.cookery.home.search.components.SearchResults
import com.cookery.common.compose.HandleEffects
import com.cookery.common.compose.components.DisplayPlaceholder
import com.cookery.common.compose.components.SnackBar
import com.skorudzhiev.cookery.ui.search.R

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
    state: SearchScreenState,
    onActionSent: (SearchActions) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    SnackBar(
        onActionSent = onActionSent,
        snackbarHostState = snackbarHostState,
        error = state.error
    )

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            isSearching = state.isSearching,
            onSearchQueryUpdate = { onActionSent(SearchMealByName(it.text)) },
            onClearSearchQuery = { onActionSent(ClearSearchQuery) }
        )

        when (state.contentType) {
            SearchResults -> {
                state.searchResults?.let { searchResults ->
                    SearchResults(
                        searchResults = searchResults,
                        onMealClicked = { onActionSent(SearchActions.OpenMealDetails(it)) }
                    )
                }
            }
            Recent -> { RecentContent(state, onActionSent) }
            Placeholder -> DisplayPlaceholder(
                image = R.drawable.search_meal,
                text = R.string.search_placeholder,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun RecentContent(
    state: SearchScreenState,
    onActionSent: (SearchActions) -> Unit,
) {
    state.recentSearches?.let { recentSearches ->
        RecentSearches(
            recentSearches = recentSearches,
            onClearRecentSearches = { onActionSent(SearchActions.CleanRecentSearchResults) },
            onUseRecentSearch = { onActionSent(SearchMealByName(it)) }
        )
    }
    Spacer(modifier = Modifier.padding(top = 16.dp))
    state.lastOpenedMeals?.let { lastOpenedMeals ->
        LastOpenedMeals(
            lastOpenedMeals = lastOpenedMeals,
            onMealClicked = { onActionSent(SearchActions.OpenMealDetails(it)) }
        )
    }
}

@Composable
private fun SnackBar(
    onActionSent: (SearchActions) -> Unit,
    snackbarHostState: SnackbarHostState,
    error: String?,
) {
    SnackBar(
        onClearError = { onActionSent(ClearError) },
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
        state = SearchScreenState(
            recentSearches = emptyList(),
            lastOpenedMeals = emptyList(),
            searchResults = emptyList(),
            isSearching = false,
            error = null,
            contentType = SearchResults
        )
    ) {
        // no-op
    }
}
