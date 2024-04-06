package com.cookery.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cookery.db.entities.Ingredient
import app.cookery.db.entities.MealDetails
import app.cookery.db.entities.getIngredients
import com.cookery.common.compose.components.CaptionText
import com.cookery.common.compose.components.CookeryDivider
import com.cookery.common.compose.components.DrawCircle
import com.cookery.common.compose.components.ExpandingText
import com.cookery.common.compose.theme.CookeryLightColors
import com.cookery.common.compose.theme.CookeryTypography
import com.cookery.common.compose.theme.youTubeButtonColor
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.navigationBarsPadding
import com.skorudzhiev.cookery.common.ui.compose.R

@Composable
fun CommonPageColumn(
    scroll: ScrollState,
    content: @Composable () -> Unit
) {
    Column {
        SpacerPageContent()
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .testTag(stringResource(R.string.cd_meal_details))
        ) {
            Spacer(Modifier.height(gradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(imageOverlap))
                    Spacer(Modifier.height(16.dp))

                    content()

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .navigationBarsPadding(start = false, end = false)
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MealDetails(
    mealDetails: MealDetails?,
    onFavoriteButtonPressed: () -> Unit,
    isFavoritesButtonSelected: Boolean
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Column {
            mealDetails?.mealName?.let {
                Text(
                    text = it,
                    color = getThemeSecondaryColor(),
                    style = MaterialTheme.typography.h6
                )
            }
            CookeryDivider()

            MealType(
                mealDetails?.mealCategory,
                mealDetails?.mealArea,
                onFavoriteButtonPressed = onFavoriteButtonPressed,
                isFavoritesButtonSelected = isFavoritesButtonSelected,
            )
            YouTubeButton(mealYouTubeId = mealDetails?.mealYoutube)

            Ingredients(mealDetails)
            Spacer(Modifier.height(8.dp))
            CookeryDivider()
            mealDetails?.cookingInstructions?.let {
                Text(
                    text = stringResource(R.string.meal_details_directions_label),
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 8.dp,
                        bottom = 8.dp
                    )
                )

                ExpandingText(
                    text = it,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                mealDetails?.mealTags?.let { MealTags(tags = it) }
            }
        }
    }
}

@Composable
private fun MealType(
    mealCategory: String?,
    mealArea: String?,
    onFavoriteButtonPressed: () -> Unit,
    isFavoritesButtonSelected: Boolean
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CaptionText(stringResource(R.string.meal_details_category_type))
        mealCategory?.let { CaptionText(text = " $it") }

        DrawCircle(paddingStart = 16.dp, size = 8f)
        Spacer(Modifier.width(16.dp))

        CaptionText(stringResource(R.string.meal_details_area_type))
        mealArea?.let { CaptionText(text = " $it") }

        DrawCircle(paddingStart = 16.dp, size = 8f)
        Spacer(Modifier.width(16.dp))

        FavoritesButton(
            onFavoriteButtonPressed = onFavoriteButtonPressed,
            isSelected = isFavoritesButtonSelected
        )
    }
}

@Composable
fun FavoritesButton(
    onFavoriteButtonPressed: () -> Unit,
    isSelected: Boolean
) {
    IconButton(onClick = onFavoriteButtonPressed) {
        Icon(
            imageVector = if (isSelected) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.cd_favorites_button)
        )
    }
}

@Composable
private fun YouTubeButton(mealYouTubeId: String?) {
    val context = LocalContext.current
    mealYouTubeId?.let {
        if (it.isEmpty()) {
            return@let
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(16.dp))
            OutlinedButton(
                onClick = {
                    openYoutubeLink(youtubeID = it, context)
                }
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_play_arrow),
                    contentDescription = stringResource(R.string.cd_youtube_button)
                )
                Text(
                    text = stringResource(R.string.meal_details_youtube_text),
                    modifier = Modifier.padding(top = 4.dp),
                    color = youTubeButtonColor
                )
            }
        }
    }
}

@Composable
private fun Ingredients(mealDetails: MealDetails?) {
    val ingredients = getIngredients(mealDetails)

    Text(
        text = stringResource(R.string.meal_details_ingredients_label),
        modifier = Modifier.padding(
            start = 8.dp,
            bottom = 8.dp
        )
    )
    for (ingredient in ingredients) {
        if (ingredient?.ingredient.isNullOrEmpty()) {
            return
        }
        Ingredient(
            ingredient = ingredient
        )
    }
}

@Composable
private fun Ingredient(
    ingredient: Ingredient?
) {
    val measure = ingredient?.let {
        if (it.measure == null) {
            "${it.ingredient}"
        } else {
            "${it.measure} ${it.ingredient}"
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        DrawCircle(
            paddingStart = 16.dp,
            size = 16f,
            color = getThemeSecondaryColor()
        )

        Spacer(Modifier.width(8.dp))
        if (measure != null) {
            Text(
                text = measure,
                Modifier.padding(
                    top = 2.dp,
                    start = 16.dp
                ),
                style = CookeryTypography.caption
            )
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun MealTags(tags: String) {
    tags.split(",")
        .toList()
        .forEach { tag ->
            Tag(tag)
        }
}

@Composable
private fun Tag(
    tag: String
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = tag,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

private fun getThemeSecondaryColor(): Color {
    return CookeryLightColors.secondary
}
