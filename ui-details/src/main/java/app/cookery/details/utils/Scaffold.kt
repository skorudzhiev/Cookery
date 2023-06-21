package app.cookery.details.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import com.cookery.api.UiError
import com.cookery.common.compose.components.CategoryDetailsAppBar
import com.cookery.common.compose.components.SnackBar

private var appBarHeight by mutableStateOf(0)

@Composable
fun DetailsScaffold(
    title: String?,
    uiError: UiError?,
    pageContent: @Composable (
        showAppBackground: Boolean,
        contentPadding: PaddingValues,
        lazyListState: LazyListState
    ) -> Unit,
    onNavigateUp: () -> Unit,
    onClearError: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    val showAppBarBackground by remember {
        derivedStateOf {
            val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
            when {
                visibleItemsInfo.isEmpty() -> false
                appBarHeight <= 0 -> false
                else -> {
                    val firstVisibleItem = visibleItemsInfo[0]
                    when {
                        firstVisibleItem.index > 0 -> true
                        else -> firstVisibleItem.size + firstVisibleItem.offset <= appBarHeight
                    }
                }
            }
        }
    }

    LaunchedEffect(uiError) {
        uiError?.let { error ->
            scaffoldState.snackbarHostState.showSnackbar(error.message)
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                showAppBarBackground = showAppBarBackground,
                onNavigateUp = onNavigateUp
            )
        },
        snackbarHost = { snackBarHostState ->
            SnackBar(
                clearError = onClearError,
                snackbarHostState = snackBarHostState
            )
        },
    ) { contentPadding ->
        pageContent(
            showAppBackground = showAppBarBackground,
            contentPadding = contentPadding,
            lazyListState = listState,
        )
    }
}

@Composable
private fun AppBar(
    title: String?,
    showAppBarBackground: Boolean,
    onNavigateUp: () -> Unit,
) {
    title?.let {
        CategoryDetailsAppBar(
            title = it,
            showAppBarBackground = showAppBarBackground,
            onNavigateUp = onNavigateUp,
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { modifier -> appBarHeight = modifier.height }
        )
    }
}
