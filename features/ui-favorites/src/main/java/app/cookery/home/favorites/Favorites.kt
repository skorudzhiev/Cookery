package app.cookery.home.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.db.entities.categories.CategoryDetails
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.Header
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.skorudzhiev.cookery.common.ui.compose.R
import com.skorudzhiev.cookery.ui.favorites.R as FavoritesRes

@Composable
fun Favorites(openMealDetails: (String) -> Unit) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = FavoritesViewState.Empty)

    Favorites(
        viewState = viewState,
        listeners = remember {
            FavoritesListeners(
                onClearError = { viewModel.clearError() },
                openMealDetails = openMealDetails
            )
        }
    )
}

@Composable
private fun Favorites(
    viewState: FavoritesViewState,
    listeners: FavoritesListeners
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(viewState.error) {
        viewState.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(error.message)
        }
    }

    Scaffold(
        snackbarHost = { state ->
            SnackBar(
                clearError = listeners.onClearError,
                snackbarHostState = state
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .bodyWidth()
                .padding(
                    rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.systemBars,
                        applyBottom = true,
                        applyTop = true
                    )
                )
        ) {
            LazyColumn(
                modifier = Modifier.background(getAppBarColor()),
                contentPadding = contentPadding
            ) {
                if (viewState.favorites.isNotEmpty()) {
                    headerItem()
                    favoritesItem(viewState, listeners)
                } else {
                    placeholderItem()
                }
            }
        }
    }
}

private fun LazyListScope.placeholderItem() {
    item {
        DisplayPlaceholder(Modifier.fillParentMaxSize())
    }
}

private fun LazyListScope.headerItem() {
    item {
        Header(
            title = stringResource(R.string.favorites_screen_title),
            showAppBarBackground = false
        )
    }
}

private fun LazyListScope.favoritesItem(
    viewState: FavoritesViewState,
    listeners: FavoritesListeners
) {
    items(viewState.favorites) { meal ->
        DisplayFavoriteMeals(
            categoryDetails = meal,
            openMealDetails = listeners.openMealDetails
        )
    }
}

@Composable
private fun DisplayFavoriteMeals(
    categoryDetails: CategoryDetails,
    openMealDetails: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { openMealDetails(categoryDetails.mealId) })
            .fillMaxSize()
            .padding(
                horizontal = Layout.bodyMargin,
                vertical = 2.dp
            )
    ) {
        CategoryDetailsItem(
            imageUrl = categoryDetails.mealImage,
            mealDescription = categoryDetails.mealName
        )
    }
}

@Composable
private fun DisplayPlaceholder(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(FavoritesRes.drawable.ic_favorites_placeholder),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.favorites_screen_placeholder)
        )
    }
}

private data class FavoritesListeners(
    val openMealDetails: (String) -> Unit,
    val onClearError: () -> Unit
)
