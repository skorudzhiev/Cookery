package com.cookery

import app.cookery.Logger
import app.cookery.data.InvokeError
import app.cookery.data.InvokeStarted
import app.cookery.data.InvokeStatus
import app.cookery.data.InvokeSuccess
import com.cookery.api.UiError
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun Flow<InvokeStatus>.watchStatus(
    loadingCounter: ObservableLoadingCounter,
    viewModelScope: CoroutineScope,
    logger: Logger,
    snackbarManager: SnackbarManager
) {
    viewModelScope.launch { collectStatus(loadingCounter, logger, snackbarManager) }
}

private suspend fun Flow<InvokeStatus>.collectStatus(
    loadingCounter: ObservableLoadingCounter,
    logger: Logger,
    snackbarManager: SnackbarManager
) {
    collect { status ->
        when (status) {
            InvokeStarted -> loadingCounter.addLoader()
            InvokeSuccess -> loadingCounter.removeLoader()
            is InvokeError -> {
                logger.i(status.throwable)
                snackbarManager.addError(UiError(status.throwable))
                loadingCounter.removeLoader()
            }
        }
    }
}
