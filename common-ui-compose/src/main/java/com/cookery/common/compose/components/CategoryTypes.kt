package com.cookery.common.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cookery.db.entities.categories.Area
import app.cookery.db.entities.categories.Category
import app.cookery.db.entities.categories.CategoryDetails
import com.cookery.common.compose.theme.getThemeColorForImageBorder
import com.cookery.common.compose.theme.getThemePrimaryColor

@Composable
internal fun Areas(
    areas: List<Area>,
    openAreaDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(start = 8.dp, bottom = 4.dp),
        horizontalArrangement = spacedBy(4.dp),
        contentPadding = PaddingValues(start = 2.dp, end = 2.dp)
    ) {
        items(areas) { area ->
            AreaItem(
                area = area.area,
                onAreaClicked = openAreaDetails,
                modifier = modifier
            )
        }
    }
}

@Composable
internal fun RandomizedMeals(
    categoryMeals: List<CategoryDetails>,
    openMealDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(start = 8.dp),
        horizontalArrangement = spacedBy(4.dp),
        contentPadding = PaddingValues(start = 2.dp, end = 2.dp)
    ) {
        items(categoryMeals) { meal ->
            RandomizedMealItem(
                meal = meal,
                openMealDetails = openMealDetails
            )
        }
    }
}

@Composable
internal fun RandomizedMealItem(
    meal: CategoryDetails,
    openMealDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    openMealDetails(meal.mealId)
                }
            )
            .fillMaxSize()
    ) {
        HorizontalItemHome(
            imageUrl = meal.mealImage,
            mealDescription = meal.mealName
        )
    }
}

@Composable
internal fun HighlightedCategories(
    categories: List<Category>,
    openCategoryDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(start = 8.dp),
        horizontalArrangement = spacedBy(16.dp)
    ) {
        items(categories) { category ->
            HighlightedCategoryItem(
                category = category,
                openCategoryDetails = openCategoryDetails
            )
        }
    }
}

@Composable
internal fun HighlightedCategoryItem(
    category: Category,
    openCategoryDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    openCategoryDetails(category.categoryName)
                }
            )
            .fillMaxSize()
    ) {
        VerticalItemHome(
            categoryType = category.categoryName,
            imageUrl = category.categoryImage
        )
    }
}

@Composable
private fun Categories(
    meals: List<Category>,
    openDetailsScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = spacedBy(2.dp),
        contentPadding = PaddingValues(start = 14.dp, end = 12.dp)
    ) {
        items(meals) { category ->
            CategoryItem(
                category = category,
                openDetailsScreen = openDetailsScreen
            )
        }
    }
}

@Composable
internal fun CategoryItem(
    category: Category,
    openDetailsScreen: (String) -> Unit,
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
                .clickable(onClick = { openDetailsScreen(category.categoryId) })
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
                Text(
                    text = category.categoryName,
                    style = MaterialTheme.typography.subtitle1,
                    color = getThemePrimaryColor(),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
