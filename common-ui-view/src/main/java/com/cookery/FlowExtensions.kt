package com.cookery

import app.cookery.Logger
import app.cookery.presentation.InvokeStatus
import app.cookery.presentation.InvokeStatus.InvokeError
import app.cookery.presentation.InvokeStatus.InvokeStarted
import app.cookery.presentation.InvokeStatus.InvokeSuccess
import app.cookery.presentation.getUiError
import com.cookery.ui.SnackbarManager
import com.cookery.util.ObservableLoadingCounter
import kotlinx.coroutines.flow.Flow

suspend fun Flow<InvokeStatus>.watchStatus(
    loadingCounter: ObservableLoadingCounter,
    logger: Logger,
    snackbarManager: SnackbarManager,
    invokeSuccess: suspend () -> Unit = {}
) {
    collect { status ->
        when (status) {
            is InvokeStarted -> loadingCounter.addLoader()
            is InvokeSuccess -> handleSuccess(
                loadingCounter = loadingCounter,
                invokeSuccess = invokeSuccess
            )
            is InvokeError -> handleError(
                loadingCounter = loadingCounter,
                snackbarManager = snackbarManager,
                status = status,
                logger = logger
            )
        }
    }
}

private suspend fun handleSuccess(
    loadingCounter: ObservableLoadingCounter,
    invokeSuccess: suspend () -> Unit?,
) {
    loadingCounter.removeLoader()
    invokeSuccess()
}

private suspend fun handleError(
    loadingCounter: ObservableLoadingCounter,
    snackbarManager: SnackbarManager,
    status: InvokeError,
    logger: Logger,
) {
    loadingCounter.removeLoader()
    snackbarManager.addError(getUiError(status.throwable))
    logger.d(
        throwable = status.throwable,
        message = status.throwable.message
    )
}
