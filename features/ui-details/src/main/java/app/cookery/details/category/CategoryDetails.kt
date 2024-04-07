package app.cookery.details.category

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.details.utils.ScaffoldDetails
import app.cookery.details.utils.backDropImage
import app.cookery.details.utils.description
import app.cookery.details.utils.spacer
import app.cookery.details.utils.titleItem
import app.cookery.domain.model.CategoryWithCategoryDetails
import com.cookery.common.compose.components.CategoryDetailsItem
import com.cookery.common.compose.components.getAppBarColor
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.modifiers.bodyWidth
import com.cookery.common.compose.modifiers.copy
import com.cookery.common.compose.modifiers.pageDetailsPadding
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.skorudzhiev.cookery.common.ui.compose.R

@Composable
fun CategoryDetails(
    navigateUp: () -> Unit,
    openMealDetails: (String) -> Unit
) {
    val viewModel: CategoryDetailsViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = CategoryDetailsViewState.Empty)

    CategoryDetails(
        viewState = viewState,
        listeners = remember {
            CategoryDetailsListeners(
                onNavigateUp = navigateUp,
                clearError = { viewModel.submitAction(CategoryDetailsAction.ClearError) },
                openMealDetails = openMealDetails
            )
        }
    )
}

@Composable
private fun CategoryDetails(
    viewState: CategoryDetailsViewState,
    listeners: CategoryDetailsListeners
) {
    val title = viewState.categoryWithCategoryDetails.getOrNull(0)?.categoryName

    ScaffoldDetails(
        title = title,
        uiError = viewState.error,
        pageContent = { showAppBarBackground, contentPadding, listState ->
            PageContent(
                viewState = viewState,
                listState = listState,
                showAppBarBackground = showAppBarBackground,
                contentPadding = contentPadding,
                openMealDetails = listeners.openMealDetails
            )
        },
        onNavigateUp = listeners.onNavigateUp,
        onClearError = listeners.clearError
    )
}

@Composable
private fun PageContent(
    viewState: CategoryDetailsViewState,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    contentPadding: PaddingValues,
    openMealDetails: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .bodyWidth()
            .padding(pageDetailsPadding())
    ) {
        PageDetails(
            categoryDetails = viewState.categoryWithCategoryDetails,
            listState = listState,
            showAppBarBackground = showAppBarBackground,
            openMealDetails = openMealDetails,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun PageDetails(
    categoryDetails: List<CategoryWithCategoryDetails>,
    listState: LazyListState,
    showAppBarBackground: Boolean,
    openMealDetails: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = listState,
        contentPadding = contentPadding.copy(copyTop = false),
        modifier = Modifier
            .background(getAppBarColor())
            .testTag(stringResource(R.string.cd_category_details))
    ) {
        val image = categoryDetails.getOrNull(0)?.categoryImage
        val title = categoryDetails.getOrNull(0)?.categoryName
        val description = categoryDetails.getOrNull(0)?.categoryDescription

        backDropImage(image, listState)
        spacer()
        titleItem(title, showAppBarBackground)
        description(description)
        pageDetailsItems(categoryDetails, modifier, openMealDetails)
    }
}

private fun LazyListScope.pageDetailsItems(
    categoryDetails: List<CategoryWithCategoryDetails>,
    modifier: Modifier,
    openMealDetails: (String) -> Unit
) = items(categoryDetails) { meal ->
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

private data class CategoryDetailsListeners(
    val onNavigateUp: () -> Unit,
    val openMealDetails: (String) -> Unit,
    val clearError: () -> Unit
)
