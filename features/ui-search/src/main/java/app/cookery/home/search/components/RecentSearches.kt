package app.cookery.home.search.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.skorudzhiev.cookery.ui.search.R

@Composable
fun RecentSearches(
    recentSearches: List<String>,
    onClearRecentSearches: () -> Unit,
    onUseRecentSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    HeaderAndTextButtonRow(
        headerText = R.string.label_recent_searches,
        buttonText = R.string.label_clear_all_recent_searches,
        modifier = modifier,
        onClick = onClearRecentSearches
    )
    Spacer(Modifier.padding(top = 8.dp))

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
private fun RecentSearch(
    recentSearch: String,
    onRecentSearchClicked: (String) -> Unit
) {
    ClickableText(
        text = AnnotatedString(text = recentSearch),
        onClick = { onRecentSearchClicked(recentSearch) }
    )
}
