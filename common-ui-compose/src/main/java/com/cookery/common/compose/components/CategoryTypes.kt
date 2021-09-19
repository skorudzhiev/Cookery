package com.cookery.common.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.cookery.data.entities.categories.AllMealCategories
import app.cookery.data.entities.categories.Category
import com.cookery.common.compose.theme.getThemeColorForImageBorder
import com.cookery.common.compose.theme.getThemePrimaryColor

@Composable
fun CategoryTypes(
    mealCategories: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    index: Int = 0,
    highlight: Boolean = false
) {
    Column(modifier = modifier) {
        // TODO: Create a conditional logic to handle different type of items
        //          for the moment, display them this way, only for debugging purposes
        CategoryTitle(title = "Category type")
        Spacer(modifier = Modifier.height(8.dp))
        RandomizedMeals(
            modifier = modifier
                .padding(
                    bottom = 16.dp
                ),
            meals = mealCategories,
            onMealClicked = onMealClicked
        )
        CookeryDivider(thickness = 2.dp)

        Spacer(modifier = Modifier.height(32.dp))

        CategoryTitle(title = "Category type2")
        Spacer(modifier = Modifier.height(8.dp))
        HighlightedCategories(
            meals = mealCategories,
            onMealClicked = onMealClicked
        )
        CookeryDivider(thickness = 2.dp)

        Spacer(modifier = Modifier.height(32.dp))

        CategoryTitle(title = "Category type3")
        Spacer(modifier = Modifier.height(8.dp))
        RandomizedMeals(
            modifier = modifier
                .padding(
                    bottom = 16.dp
                ),
            meals = mealCategories,
            onMealClicked = onMealClicked
        )
        CookeryDivider(thickness = 2.dp)

        Spacer(modifier = Modifier.height(32.dp))

        CategoryTitle(title = "Category type4")
        Spacer(modifier = Modifier.height(8.dp))
        Categories(
            meals = mealCategories,
            onMealClicked = onMealClicked
        )
        CookeryDivider(thickness = 2.dp)
    }
}

@Composable
private fun CategoryTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 24.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = getThemePrimaryColor().copy(alpha = 0.5f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
    }
}

@Composable
private fun RandomizedMeals(
    meals: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = spacedBy(4.dp),
        contentPadding = PaddingValues(start = 2.dp, end = 2.dp)
    ) {
        items(meals.categories) { meal ->
            RandomizedMealItem(
                category = meal,
                onMealClicked = onMealClicked
            )
        }
    }
}

@Composable
private fun RandomizedMealItem(
    category: Category,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = { onMealClicked(category.categoryId) })
            .fillMaxSize()
            .padding(start = 16.dp)
    ) {
        HorizontalItemHome(
            imageUrl = category.categoryImage,
            mealDescription = category.categoryType
        )
    }
}

@Composable
private fun HighlightedCategories(
    meals: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)
    LazyRow(
        modifier = modifier
            .padding(bottom = 12.dp),
        horizontalArrangement = spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 12.dp)
    ) {
        items(meals.categories) { category ->
            HighlightedCategoryItem(
                category = category,
                onMealClicked = onMealClicked
            )
        }
    }
}

@Composable
private fun HighlightedCategoryItem(
    category: Category,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = { onMealClicked(category.categoryId) })
            .fillMaxSize()
    ) {
        VerticalItemHome(
            categoryType = category.categoryType,
            imageUrl = category.categoryImage
        )
    }
}

@Composable
private fun Categories(
    meals: AllMealCategories,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = spacedBy(2.dp),
        contentPadding = PaddingValues(start = 14.dp, end = 12.dp)
    ) {
        items(meals.categories) { category ->
            CategoryItem(
                category = category,
                onMealClicked = onMealClicked
            )
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    onMealClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp
        )
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = { onMealClicked(category.categoryId) })
                .padding(8.dp)
        ) {
            category.categoryImage?.let {
                CircularBorderImage(
                    imageUrl = it,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    borderStrokeColor = getThemeColorForImageBorder(),
                    borderStrokeSize = 2.dp
                )
                category.categoryType?.let { mealCategory ->
                    Text(
                        text = mealCategory,
                        style = MaterialTheme.typography.subtitle1,
                        color = getThemePrimaryColor(),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}