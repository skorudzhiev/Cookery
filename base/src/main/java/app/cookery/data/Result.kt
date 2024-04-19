package app.cookery.data

sealed class Result<out T> {
    open fun get(): T? = null

    fun getOrThrow(): T = when (this) {
        is Success -> get()
        is ErrorResult -> throw throwable
    }
}

data class Success<out T>(val data: T) : Result<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(val throwable: Throwable) : Result<T>()

inline fun <T> Result<T>.onSuccess(
    onSuccess: (value: T) -> Unit,
): Result<T> {
    if (this is Success) onSuccess(data)
    return this
}

inline fun <T> Result<T>.onFailure(
    onFailure: (throwable: Throwable) -> Unit,
): Result<T> {
    if (this is ErrorResult) onFailure(throwable)
    return this
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (throwable: Throwable) -> R,
): R = when (this) {
    is Success -> onSuccess(data)
    is ErrorResult -> onFailure(throwable)
}
