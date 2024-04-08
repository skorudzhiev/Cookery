package app.cookery.presentation

sealed class InvokeStatus {
    data object InvokeStarted : InvokeStatus()
    data object InvokeSuccess : InvokeStatus()
    data class InvokeError(val throwable: Throwable) : InvokeStatus()
}
