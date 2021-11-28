package app.cookery.home.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cookery.common.compose.components.Collection
import com.cookery.common.compose.components.CollectionWithHeader
import com.cookery.common.compose.components.SwipeDismissSnackbar
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Categories(
    openMealsDetails: (String) -> Unit,
    openCategoryDetails: (String) -> Unit,
    openAreaDetails: (String) -> Unit
) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = CategoriesViewState.Empty)

    Categories(
        state = viewState,
        refresh = { viewModel.submitAction(CategoriesAction.RefreshAction) },
        clearError = { viewModel.submitAction(CategoriesAction.ClearError) },
        openMealsDetails = openMealsDetails,
        openCategoryDetails = openCategoryDetails,
        openAreaDetails = openAreaDetails
    )
}

@Composable
internal fun Categories(
    state: CategoriesViewState,
    refresh: () -> Unit,
    clearError: (CategoriesAction) -> Unit,
    openMealsDetails: (String) -> Unit,
    openCategoryDetails: (String) -> Unit,
    openAreaDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.refreshing),
            onRefresh = refresh,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    scale = true
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.bodyWidth()
            ) {
                item {
                    Spacer(Modifier.height(Layout.gutter))
                }

                item {
                    CollectionWithHeader(
                        items = state.popularMeals,
                        title = stringResource(R.string.category_type_popular),
                        refreshing = state.randomCategoriesRefreshing,
                        openMealsDetails = openMealsDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.mealCategories,
                        title = stringResource(R.string.category_type_all_categories),
                        refreshing = state.categoriesRefreshing,
                        openMealsDetails = openMealsDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.recommendedMeals,
                        title = stringResource(R.string.category_type_recommended),
                        refreshing = state.randomAreasRefreshing,
                        openMealsDetails = openMealsDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.areaMeals.take(14),
                        title = stringResource(R.string.category_type_areas),
                        refreshing = state.areasRefreshing,
                        openMealsDetails = openMealsDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    Collection(
                        items = state.areaMeals.takeLast(13),
                        title = stringResource(R.string.category_type_areas),
                        openMealDetails = openMealsDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }
            }

            SnackBar(
                state = state,
                clearError = clearError
            )
        }
    }
}

@Composable
private fun SnackBar(
    state: CategoriesViewState,
    clearError: (CategoriesAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        val snackbarHostState = remember { SnackbarHostState() }

        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = {
                SwipeDismissSnackbar(
                    data = it,
                    onDismiss = { clearError(CategoriesAction.ClearError) }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )

        LaunchedEffect(state.error) {
            state.error?.let { error ->
                snackbarHostState.showSnackbar(error.message)
            }
        }
    }
}
