package com.cookery.api

import retrofit2.HttpException
import java.io.IOException

sealed class UiStatus

data class UiError(val message: String) : UiStatus()
fun UiError(throwable: Throwable): UiError {
    return when (throwable) {
        is HttpException -> UiError(throwable.message ?: "Error occurred: $throwable")
        is IOException -> UiError("Couldn't reach server. Check your internet connection.")
        else -> UiError(throwable.message ?: "Error occurred: $throwable")
    }
}
