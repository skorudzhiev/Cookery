package app.cookery.home.categories

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.DataStoreManager
import com.cookery.common.compose.components.Collection
import com.cookery.common.compose.components.CollectionWithHeader
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collect

@Composable
fun Categories(
    openMealDetails: (String, String) -> Unit,
    openCategoryDetails: (String, String) -> Unit,
    openAreaDetails: (String, String) -> Unit
) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = CategoriesViewState.Empty)

    InitializeAppData(viewModel)

    Categories(
        state = viewState,
        refresh = { viewModel.submitAction(CategoriesAction.RefreshAction) },
        openMealDetails = openMealDetails,
        openCategoryDetails = openCategoryDetails,
        openAreaDetails = openAreaDetails
    )
}

@Composable
internal fun InitializeAppData(viewModel: CategoriesViewModel) {
    val context = LocalContext.current
    val dataStore = DataStoreManager(context)

    LaunchedEffect(
        key1 = Unit,
        block = {
            dataStore.isAppDataInitialized().collect { isAppDataInitialized ->
                if (!isAppDataInitialized) {
                    viewModel.submitAction(CategoriesAction.InitializeData)
                    dataStore.saveAppInitializationState(true)
                }
            }
        }
    )
}

@Composable
internal fun Categories(
    state: CategoriesViewState,
    refresh: () -> Unit,
    openMealDetails: (String, String) -> Unit,
    openCategoryDetails: (String, String) -> Unit,
    openAreaDetails: (String, String) -> Unit,
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
                        openMealDetails = openMealDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.mealCategories,
                        title = stringResource(R.string.category_type_all_categories),
                        refreshing = state.categoriesRefreshing,
                        openMealDetails = openMealDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.recommendedMeals,
                        title = stringResource(R.string.category_type_recommended),
                        refreshing = state.randomAreasRefreshing,
                        openMealDetails = openMealDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    CollectionWithHeader(
                        items = state.areaMeals.take(14),
                        title = stringResource(R.string.category_type_areas),
                        refreshing = state.areasRefreshing,
                        openMealDetails = openMealDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }

                item {
                    Collection(
                        items = state.areaMeals.takeLast(13),
                        title = stringResource(R.string.category_type_areas),
                        openMealDetails = openMealDetails,
                        openCategoryDetails = openCategoryDetails,
                        openAreaDetails = openAreaDetails
                    )
                }
            }
        }
    }
}
