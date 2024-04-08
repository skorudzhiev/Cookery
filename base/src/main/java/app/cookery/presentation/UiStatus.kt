package app.cookery.presentation

import app.cookery.presentation.CookeryError.BadRequest
import app.cookery.presentation.CookeryError.Expired
import app.cookery.presentation.CookeryError.Forbidden
import app.cookery.presentation.CookeryError.IllegalArgument
import app.cookery.presentation.CookeryError.NetworkOffline
import app.cookery.presentation.CookeryError.NetworkTimeout
import app.cookery.presentation.CookeryError.Unauthorized
import app.cookery.presentation.CookeryError.Unknown
import app.cookery.presentation.UiStatus.UiError
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class UiStatus : CookeryError() {

    data class UiError(
        val errorType: CookeryError,
        val throwable: Throwable,
    ) : UiStatus()
}

fun getUiError(throwable: Throwable): UiError {
    return when (throwable) {
        is UnknownHostException -> UiError(errorType = NetworkOffline, throwable = throwable)
        is SocketTimeoutException -> UiError(errorType = NetworkTimeout, throwable = throwable)
        is IllegalArgumentException -> UiError(errorType = IllegalArgument, throwable = throwable)
        is HttpException -> UiError(errorType = throwable.exceptionType(), throwable = throwable)
        else -> UiError(errorType = Unknown, throwable = throwable)
    }
}

fun HttpException.exceptionType(): CookeryError {
    return when (code()) {
        HttpURLConnection.HTTP_BAD_REQUEST -> BadRequest
        HttpURLConnection.HTTP_UNAUTHORIZED -> Unauthorized
        HttpURLConnection.HTTP_GONE -> Expired
        HttpURLConnection.HTTP_FORBIDDEN -> Forbidden
        else -> Unknown
    }
}
