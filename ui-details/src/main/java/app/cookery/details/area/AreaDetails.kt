package app.cookery.details.area

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.db.entities.relations.AreaWithCategoryDetails
import app.cookery.details.utils.ScaffoldDetails
import app.cookery.details.utils.backDropImage
import app.cookery.details.utils.spacer
import app.cookery.details.utils.titleItem
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.modifiers.copy
import com.cookery.common.compose.modifiers.pageDetailsPadding
import com.cookery.common.compose.rememberFlowWithLifecycle

@Composable
fun AreaDetails(
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    val viewModel: AreaDetailsViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = AreaDetailsViewState.Empty)

    AreaDetails(
        viewState = viewState,
        listeners = remember {
            AreaDetailsListeners(
                onNavigateUp = navigateUp,
                clearError = { viewModel.clearError() },
                openMealDetails = openMealDetails
            )
        }
    )
}

@Composable
private fun AreaDetails(
    viewState: AreaDetailsViewState,
    listeners: AreaDetailsListeners
) {
    val title = viewState.areaWithCategoryDetails.getOrNull(0)?.area

    ScaffoldDetails(
        title = title,
        uiError = viewState.error,
        pageContent = { showAppBackground, contentPadding, listState ->
            PageContent(
                viewState = viewState,
                listState = listState,
                showAppBarBackground = showAppBackground,
                listeners = listeners,
                contentPadding = contentPadding
            )
        },
        onNavigateUp = listeners.onNavigateUp,
        onClearError = listeners.clearError,
    )
}

@Composable
private fun PageContent(
    viewState: AreaDetailsViewState,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    listeners: AreaDetailsListeners,
    contentPadding: PaddingValues
) {
    Surface(
        modifier = Modifier
            .bodyWidth()
            .padding(pageDetailsPadding())
    ) {
        PageList(
            areaDetails = viewState.areaWithCategoryDetails,
            listState = listState,
            showAppBarBackground = showAppBarBackground,
            openMealDetails = listeners.openMealDetails,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun PageList(
    areaDetails: List<AreaWithCategoryDetails>,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    openMealDetails: (String) -> Unit,
    contentPadding: PaddingValues
) {
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
        backDropImage(backdrop, listState)
        spacer()
        titleItem(title, showAppBarBackground)
        pageDetailsItems(areaDetails, openMealDetails)
    }
}

private fun LazyListScope.pageDetailsItems(
    areaDetails: List<AreaWithCategoryDetails>,
    openMealDetails: (String) -> Unit
) {
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

private data class AreaDetailsListeners(
    val onNavigateUp: () -> Unit,
    val clearError: () -> Unit,
    val openMealDetails: (String) -> Unit
)
