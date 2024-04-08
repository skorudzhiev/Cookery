package com.cookery.ui

import app.cookery.extensions.delayFlow
import app.cookery.presentation.UiStatus.UiError
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.receiveAsFlow
import org.threeten.bp.Duration
import javax.inject.Inject

class SnackbarManager @Inject constructor() {
    // We want a maximum of 3 errors queued
    private val pendingErrors = Channel<UiError>(3, BufferOverflow.DROP_OLDEST)
    private val removeErrorSignal = Channel<Unit>(Channel.RENDEZVOUS)

    /**
     * A flow of [UiError]s to display in the UI, usually as snackbars. The flow will immediately
     * emit `null`, and will then emit errors sent via [addError]. Once 6 seconds has elapsed,
     * or [removeCurrentError] is called (if before that) `null` will be emitted to remove
     * the current error.
     */
    val errors: Flow<UiError?> = flow {
        emit(null)

        pendingErrors.receiveAsFlow().collect {
            emit(it)

            // Wait for either a 6 second timeout, or a remove signal (whichever comes first)
            merge(
                delayFlow(Duration.ofSeconds(6).toMillis(), Unit),
                removeErrorSignal.receiveAsFlow(),
            ).firstOrNull()

            // Remove the error
            emit(null)
        }
    }

    /**
     * Add [error] to the queue of errors to display.
     */
    suspend fun addError(error: UiError) {
        pendingErrors.send(error)
    }

    /**
     * Remove the current error from being displayed.
     */
    suspend fun removeCurrentError() {
        removeErrorSignal.send(Unit)
    }
}
