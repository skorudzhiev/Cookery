package app.cookery.details.meal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cookery.data.entities.Ingredient
import app.cookery.data.entities.MealDetails
import app.cookery.data.entities.getIngredients
import app.cookery.details.R
import app.cookery.details.meal.MealDetailsAction.ClearError
import app.cookery.details.meal.MealDetailsAction.UpdateFavoriteMeal
import app.cookery.details.utils.openYoutubeLink
import coil.annotation.ExperimentalCoilApi
import com.cookery.api.UiError
import com.cookery.common.compose.components.CaptionText
import com.cookery.common.compose.components.CircularBorderImage
import com.cookery.common.compose.components.CookeryDivider
import com.cookery.common.compose.components.DrawCircle
import com.cookery.common.compose.components.ExpandingText
import com.cookery.common.compose.components.SnackBar
import com.cookery.common.compose.rememberFlowWithLifecycle
import com.cookery.common.compose.theme.CookeryLightColors
import com.cookery.common.compose.theme.CookeryTypography
import com.cookery.common.compose.theme.youTubeButtonColor
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun MealDetails(
    navigateUp: () -> Unit
) {
    val viewModel: MealDetailsViewModel = hiltViewModel()
    val viewState by rememberFlowWithLifecycle(viewModel.state)
        .collectAsState(initial = MealDetailsViewState.Empty)

    MealDetails(
        state = viewState,
        listeners = remember {
            MealDetailsListeners(
                navigateUp = navigateUp,
                updateFavoriteMeal = { viewModel.submitAction(UpdateFavoriteMeal) },
                clearError = { viewModel.submitAction(ClearError) }
            )
        }
    )
}

@Composable
private fun MealDetails(
    state: MealDetailsViewState,
    listeners: MealDetailsListeners
) {
    val scroll = rememberScrollState(0)
    val snackBarHostState = remember { SnackbarHostState() }

    SnackBar(
        error = state.error,
        snackBarHostState = snackBarHostState,
        onClearError = listeners.clearError
    )

    Box(
        Modifier
            .fillMaxSize()
            .padding(
                rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.systemBars,
                    applyBottom = true,
                    applyTop = false,
                )
            )
    ) {
        Spacer()
        BackButton(listeners.navigateUp)
        PageContent(
            state.mealDetails,
            scroll,
            onFavoriteButtonPressed = listeners.updateFavoriteMeal,
            isFavoritesButtonSelected = state.isMealMarkedAsFavorite
        )
        Image(state.mealDetails?.mealImage, scroll.value)
    }
}

@Composable
private fun BackButton(onNavigateUp: () -> Unit) {
    IconButton(
        onClick = onNavigateUp,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Color.Transparent,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.Black,
            contentDescription = stringResource(R.string.cd_navigate_up)
        )
    }
}

@Composable
private fun Spacer() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(CookeryLightColors.background)
    )
}

@Composable
private fun SnackBar(
    error: UiError?,
    snackBarHostState: SnackbarHostState,
    onClearError: () -> Unit
) {
    LaunchedEffect(error) {
        error?.let { error ->
            snackBarHostState.showSnackbar(error.message)
        }
    }

    SnackBar(
        clearError = onClearError,
        snackbarHostState = snackBarHostState
    )
}

@Composable
private fun PageContent(
    mealDetails: MealDetails?,
    scroll: ScrollState,
    onFavoriteButtonPressed: () -> Unit,
    isFavoritesButtonSelected: Boolean
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
                .testTag(stringResource(R.string.cd_meal_details))
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(16.dp))

                    MealDetails(
                        mealDetails,
                        onFavoriteButtonPressed = onFavoriteButtonPressed,
                        isFavoritesButtonSelected = isFavoritesButtonSelected
                    )

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

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun Image(
    imageUrl: String?,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        CircularBorderImage(
            imageUrl = imageUrl.toString(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            borderStrokeSize = 1.dp,
            borderStrokeColor = Color.White,
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Composable
private fun MealDetails(
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

private data class MealDetailsListeners(
    val navigateUp: () -> Unit,
    val updateFavoriteMeal: () -> Unit,
    val clearError: () -> Unit
)
