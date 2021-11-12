package com.cookery.common.compose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.cookery.data.entities.categories.Area
import app.cookery.data.entities.categories.Category
import app.cookery.data.entities.categories.CategoryDetails
import com.cookery.common.compose.R
import com.cookery.common.compose.modifiers.Layout
import com.cookery.common.compose.theme.getThemePrimaryColor

@Composable
fun <T> CollectionWithHeader(
    items: List<T>,
    title: String,
    refreshing: Boolean,
    openDetailsScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier) {
        if (refreshing || items.isNotEmpty()) {
            Spacer(Modifier.height(Layout.gutter))

            Header(
                title = title,
                loading = refreshing,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // TODO handle empty state - load from network, after a small delay
        HandleCategoryTypes(
            items = items,
            title = title,
            openDetailsScreen = openDetailsScreen
        )
        // todo: check for internet connection, or else
    }
}

@Suppress("UNCHECKED_CAST")
@Composable
internal fun <T> HandleCategoryTypes(
    items: List<T>,
    title: String,
    openDetailsScreen: (String, String) -> Unit
) {
    val popular = stringResource(R.string.category_type_popular)
    val categories = stringResource(R.string.category_type_all_categories)
    val recommended = stringResource(R.string.category_type_recommended)
    val areas = stringResource(R.string.category_type_areas)

    if (items.isNotEmpty()) {
        when (title) {
            popular -> RandomizedMeals(
                categoryMeals = items as List<CategoryDetails>,
                openDetailsScreen = openDetailsScreen
            )
            categories -> HighlightedCategories(
                categories = items as List<Category>,
                openCategoryDetails = openDetailsScreen
            )
            recommended -> RandomizedMeals(
                categoryMeals = items as List<CategoryDetails>,
                openDetailsScreen = openDetailsScreen
            )
            areas -> Areas(
                areas = items as List<Area>,
                openAreaDetails = openDetailsScreen
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun Header(
    title: String,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    content: @Composable RowScope.() -> Unit = {}
) {
    Row(modifier) {
        Spacer(Modifier.width(Layout.bodyMargin))

        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 8.dp)
        )

        Spacer(Modifier.weight(1f))

        AnimatedVisibility(visible = loading) {
            AutoSizedCircularProgressIndicator(
                color = getThemePrimaryColor().copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(8.dp)
                    .size(16.dp)
            )
        }

        content()

        Spacer(Modifier.width(Layout.bodyMargin))
    }
}

@Composable
fun <T> Collection(
    items: List<T>,
    title: String,
    openDetailsScreen: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Spacer(Modifier.height(Layout.gutter))

        HandleCategoryTypes(
            items = items,
            title = title,
            openDetailsScreen = openDetailsScreen
        )
    }
}
