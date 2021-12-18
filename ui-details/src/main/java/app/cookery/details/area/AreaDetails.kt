package app.cookery.details.area

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.data.entities.relations.AreaWithCategoryDetails
import com.cookery.common.compose.components.BackdropImage
import com.cookery.common.compose.components.CategoryDetailsAppBar
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.Header
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.modifiers.copy
import com.cookery.common.compose.rememberFlowWithLifecycle

@Composable
fun AreaDetails(
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    AreaDetails(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
        openMealDetails = openMealDetails
    )
}

@Composable
private fun AreaDetails(
    viewModel: AreaDetailsViewModel,
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = AreaDetailsViewState.Empty)

    val title = viewState.areaWithCategoryDetails.getOrNull(0)?.area

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
            title?.let {
                CategoryDetailsAppBar(
                    title = it,
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
                clearError = { viewModel.clearError() },
                snackbarHostState = snackBarHostState
            )
        },
    ) { contentPadding ->
        Surface(modifier = Modifier.bodyWidth()) {
            AreaDetails(
                areaDetails = viewState.areaWithCategoryDetails,
                listState = listState,
                showAppBarBackground = showAppBarBackground,
                openMealDetails = openMealDetails,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun AreaDetails(
    areaDetails: List<AreaWithCategoryDetails>,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    openMealDetails: (String) -> Unit,
    contentPadding: PaddingValues
) {
    val gutter = Layout.gutter
    val bodyMargin = Layout.bodyMargin
    var backdrop: String?
    var title: String?

    areaDetails.getOrNull(0).let {
        backdrop = it?.mealImage
        title = it?.area
    }

    LazyColumn(
        state = listState,
        contentPadding = contentPadding.copy(copyTop = false),
        modifier = Modifier.background(getAppBarColor())
    ) {
        item {
            backdrop?.let {
                BackdropImage(
                    backdropImage = it,
                    listState = listState
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(max(gutter, bodyMargin)))
        }

        item {
            title?.let { Header(it, showAppBarBackground) }
        }

        items(areaDetails) { meal ->
            Box(
                modifier = Modifier
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
                    modifier = Modifier
                )
            }
        }
    }
}
