package app.cookery.details.category

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.data.entities.relations.CategoryWithCategoryDetails
import com.cookery.common.compose.components.BackdropImage
import com.cookery.common.compose.components.CategoryDetailsAppBar
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.ExpandingText
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.modifiers.copy
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.cookery.common.compose.theme.CookeryDarkColors

@Composable
fun CategoryDetails(
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    CategoryDetails(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
        openMealDetails = openMealDetails
    )
}

@Composable
internal fun CategoryDetails(
    viewModel: CategoryDetailsViewModel,
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = CategoryDetailsViewState.Empty)

    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    var appBarHeight by remember { mutableStateOf(0) }
    val showAppBarBackground by remember {
        derivedStateOf {
            val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
            when {
                visibleItemsInfo.isEmpty() -> false
                appBarHeight <= 0 -> false
                else -> {
                    val firstVisibleItem = visibleItemsInfo[0]
                    when {
                        firstVisibleItem.index > 0 -> true
                        else -> firstVisibleItem.size + firstVisibleItem.offset <= appBarHeight
                    }
                }
            }
        }
    }

    LaunchedEffect(viewState.error) {
        viewState.error?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(error.message)
        }
    }

    Scaffold(
        topBar = {
            viewState.categoryWithCategoryDetails.getOrNull(0)?.categoryName?.let { title ->
                CategoryDetailsAppBar(
                    title = title,
                    showAppBarBackground = showAppBarBackground,
                    onNavigateUp = navigateUp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged { appBarHeight = it.height }
                )
            }
        },
        snackbarHost = { snackBarHostState ->
            SnackBar(
                clearError = { viewModel.submitAction(CategoryDetailsAction.ClearError) },
                snackbarHostState = snackBarHostState
            )
        },
    ) { contentPadding ->
        Surface(modifier = Modifier.bodyWidth()) {
            CategoryDetails(
                categoryDetails = viewState.categoryWithCategoryDetails,
                listState = listState,
                showAppBarBackground = showAppBarBackground,
                openMealDetails = openMealDetails,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun CategoryDetails(
    categoryDetails: List<CategoryWithCategoryDetails>,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    openMealDetails: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val gutter = Layout.gutter
    val bodyMargin = Layout.bodyMargin

    LazyColumn(
        state = listState,
        contentPadding = contentPadding.copy(copyTop = false),
        modifier = Modifier.background(getAppBarColor())
    ) {
        item {
            categoryDetails.getOrNull(0)?.let { categoryDetails ->
                BackdropImage(
                    backdropImage = categoryDetails.categoryImage,
                    listState = listState
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(max(gutter, bodyMargin)))
        }

        item {
            categoryDetails.getOrNull(0)?.categoryName?.let { Header(it, showAppBarBackground) }
        }

        val description = categoryDetails.getOrNull(0)?.categoryDescription
        if (description != null) {
            item {
                ExpandingText(
                    text = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Layout.bodyMargin,
                            vertical = Layout.gutter
                        )
                )
            }
        }

        items(categoryDetails) { meal ->
            Box(
                modifier = modifier
                    .clickable(onClick = { openMealDetails(meal.mealId) })
                    .fillMaxSize()
                    .padding(
                        horizontal = Layout.bodyMargin,
                        vertical = 2.dp
                    )
            ) {
                CategoryDetailsItem(
                    imageUrl = meal.mealImage,
                    mealDescription = meal.mealName,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun Header(
    title: String,
    showAppBarBackground: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Layout.bodyMargin, vertical = Layout.gutter)
    ) {
        val originalTextStyle = MaterialTheme.typography.h5

        Crossfade(!showAppBarBackground) { show ->
            if (show) Text(
                text = title,
                style = originalTextStyle.copy(
                    color = CookeryDarkColors.secondary,
                ),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
