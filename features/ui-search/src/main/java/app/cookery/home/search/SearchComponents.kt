package app.cookery.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cookery.common.compose.components.AutoSizedCircularProgressIndicator
import com.cookery.common.compose.theme.CookeryLightColors
import com.cookery.common.compose.theme.getThemePrimaryColor
import com.skorudzhiev.cookery.ui.search.R

@Composable
fun SearchBar(
    isSearching: Boolean,
    onSearchQueryUpdate: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    var searchFocused = remember { false }

    Box(modifier = modifier.searchBarModifier()) {
        if (query.text.isEmpty()) {
            SearchHint()
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        ) {
            if (searchFocused && query.text.isNotEmpty()) {
                ClearSearchQueryButton(onClearQuery = { query = TextFieldValue("") })
            }

            BasicTextField(
                value = query,
                onValueChange = {
                    query = it
                    onSearchQueryUpdate(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged {
                        searchFocused = it.isFocused
                    }
            )

            if (isSearching) {
                SearchProgressBar()
            } else {
                Spacer(Modifier.width(48.dp))
            }
        }
    }
}

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = CookeryLightColors.primary,
            contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.search_hint),
            color = CookeryLightColors.primary
        )
    }
}

@Composable
private fun ClearSearchQueryButton(onClearQuery: () -> Unit) {
    IconButton(onClick = onClearQuery) {
        Icon(
            imageVector = Icons.Outlined.Close,
            tint = CookeryLightColors.primary,
            contentDescription = null
        )
    }
}

@Composable
private fun SearchProgressBar() {
    AutoSizedCircularProgressIndicator(
        color = getThemePrimaryColor().copy(alpha = 0.5f),
        modifier = Modifier
            .padding(6.dp)
            .size(36.dp)
    )
}

@Composable
private fun Modifier.searchBarModifier() = this
    .fillMaxWidth()
    .height(56.dp)
    .padding(horizontal = 24.dp, vertical = 8.dp)
    .background(
        shape = MaterialTheme.shapes.small,
        color = CookeryLightColors.background
    )
    .clip(MaterialTheme.shapes.small)
