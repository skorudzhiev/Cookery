package com.cookery.common.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun SnackBar(
    errorMessage: String?,
    snackBarHostState: SnackbarHostState,
    onClearError: () -> Unit
) {
    LaunchedEffect(errorMessage) {
        errorMessage?.let { error ->
            snackBarHostState.showSnackbar(error)
        }
    }

    SnackBar(
        clearError = onClearError,
        snackbarHostState = snackBarHostState
    )
}

@Composable
private fun SnackBar(
    clearError: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Box {
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = {
                SwipeDismissSnackbar(
                    data = it,
                    onDismiss = clearError
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

/**
 * Wrapper around [Snackbar] to make it swipe-dismissable,
 * using [SwipeToDismiss].
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeDismissSnackbar(
    data: SnackbarData,
    onDismiss: (() -> Unit)? = null,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val dismissState = rememberDismissState {
        if (it != DismissValue.Default) {
            // First dismiss the snackbar
            data.dismiss()
            // Then invoke the callback
            onDismiss?.invoke()
        }
        true
    }

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        background = {},
        dismissContent = { snackbar(data) }
    )
}
