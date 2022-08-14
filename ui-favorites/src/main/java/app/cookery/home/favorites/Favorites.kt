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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.data.entities.categories.CategoryDetails
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.Header
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun Favorites(openMealDetails: (String) -> Unit) {
    Favorites(
        viewModel = hiltViewModel(),
        openMealDetails = openMealDetails
    )
}

@Composable
private fun Favorites(
    viewModel: FavoritesViewModel,
    openMealDetails: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = FavoritesViewState.Empty)

    LaunchedEffect(viewState.error) {
        viewState.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(error.message)
        }
    }

    Scaffold(
        snackbarHost = { state ->
            SnackBar(
                clearError = { viewModel.clearError() },
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
                    item {
                        Header(
                            title = stringResource(R.string.favorites_screen_title),
                            showAppBarBackground = false
                        )
                    }

                    items(viewState.favorites) { meal ->
                        DisplayFavoriteMeals(
                            categoryDetails = meal,
                            openMealDetails = openMealDetails
                        )
                    }
                } else {
                    item {
                        DisplayPlaceholder(Modifier.fillParentMaxSize())
                    }
                }
            }
        }
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
            painter = painterResource(R.drawable.ic_favorites_placeholder),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.favorites_screen_placeholder)
        )
    }
}
