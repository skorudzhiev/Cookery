package app.cookery.home.categories

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.cookery.common.compose.components.Collection
import com.cookery.common.compose.components.CollectionWithHeader
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private const val firstHalfAreas = 14
private const val secondHalfAreas = 13

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
        listeners = remember {
            CategoriesActionListeners(
                refresh = { viewModel.submitAction(CategoriesActions.RefreshActions) },
                clearError = { viewModel.submitAction(CategoriesActions.ClearError) },
                openMealsDetails = openMealsDetails,
                openCategoryDetails = openCategoryDetails,
                openAreaDetails = openAreaDetails
            )
        }
    )
}

@Composable
private fun Categories(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Surface(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.refreshing),
            onRefresh = listeners.refresh,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    scale = true
                )
            }
        ) {
            LazyColumn(modifier = Modifier.bodyWidth()) {
                item { Spacer(Modifier.height(Layout.gutter)) }
                popularMeals(state, listeners)
                allCategoriesMeals(state, listeners)
                recommendedMeals(state, listeners)
                areaFirstCategories(state, listeners)
                areaSecondCategories(state, listeners)
            }

            LaunchedEffect(state.error) {
                state.error?.let { error ->
                    snackbarHostState.showSnackbar(error.message)
                }
            }
            SnackBar(
                clearError = listeners.clearError,
                snackbarHostState
            )
        }
    }
}

private fun LazyListScope.popularMeals(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) = item {
    CollectionWithHeader(
        items = state.popularMeals,
        title = stringResource(R.string.category_type_popular),
        refreshing = state.randomCategoriesRefreshing,
        openMealsDetails = listeners.openMealsDetails,
        openCategoryDetails = listeners.openCategoryDetails,
        openAreaDetails = listeners.openAreaDetails,
        modifier = Modifier.testTag(stringResource(R.string.cd_popular_meals))
    )
}

private fun LazyListScope.allCategoriesMeals(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) = item {
    CollectionWithHeader(
        items = state.mealCategories,
        title = stringResource(R.string.category_type_all_categories),
        refreshing = state.categoriesRefreshing,
        openMealsDetails = listeners.openMealsDetails,
        openCategoryDetails = listeners.openCategoryDetails,
        openAreaDetails = listeners.openAreaDetails,
        modifier = Modifier.testTag(stringResource(R.string.cd_all_categories))
    )
}

private fun LazyListScope.recommendedMeals(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) = item {
    CollectionWithHeader(
        items = state.recommendedMeals,
        title = stringResource(R.string.category_type_recommended),
        refreshing = state.randomAreasRefreshing,
        openMealsDetails = listeners.openMealsDetails,
        openCategoryDetails = listeners.openCategoryDetails,
        openAreaDetails = listeners.openAreaDetails,
        modifier = Modifier.testTag(stringResource(R.string.cd_recommended_meals))
    )
}

private fun LazyListScope.areaFirstCategories(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) = item {
    CollectionWithHeader(
        items = state.areaMeals.take(firstHalfAreas),
        title = stringResource(R.string.category_type_areas),
        refreshing = state.areasRefreshing,
        openMealsDetails = listeners.openMealsDetails,
        openCategoryDetails = listeners.openCategoryDetails,
        openAreaDetails = listeners.openAreaDetails
    )
}

private fun LazyListScope.areaSecondCategories(
    state: CategoriesViewState,
    listeners: CategoriesActionListeners
) = item {
    Collection(
        items = state.areaMeals.takeLast(secondHalfAreas),
        title = stringResource(R.string.category_type_areas),
        openMealDetails = listeners.openMealsDetails,
        openCategoryDetails = listeners.openCategoryDetails,
        openAreaDetails = listeners.openAreaDetails
    )
}

private data class CategoriesActionListeners(
    val refresh: () -> Unit,
    val clearError: () -> Unit,
    val openMealsDetails: (String) -> Unit,
    val openCategoryDetails: (String) -> Unit,
    val openAreaDetails: (String) -> Unit
)
