package app.cookery.home.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cookery.common.compose.components.CaptionText
import com.skorudzhiev.cookery.ui.search.R

@Composable
fun RecentSearches(
    recentSearches: List<String>,
    onClearRecentSearches: () -> Unit,
    onUseRecentSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    RecentSearchesHeader(modifier, onClearRecentSearches)
    Spacer(Modifier.padding(top = 16.dp))

    LazyColumn {
        items(recentSearches) { item ->
            RecentSearch(
                recentSearch = item,
                onRecentSearchClicked = onUseRecentSearch
            )
        }
    }
}

@Composable
private fun RecentSearchesHeader(
    modifier: Modifier,
    onClearRecentSearches: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        CaptionText(
            text = stringResource(R.string.label_recent_searches),
            textAlign = TextAlign.Start
        )
        TextButton(onClick = onClearRecentSearches) {
            CaptionText(
                text = stringResource(R.string.label_clear_all_recent_searches),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun RecentSearch(
    recentSearch: String,
    onRecentSearchClicked: (String) -> Unit
) {
    ClickableText(
        text = AnnotatedString(text = recentSearch),
        onClick = { onRecentSearchClicked(recentSearch) }
    )
}
