package app.cookery.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> delayFlow(timeout: Long, value: T): Flow<T> = flow {
    delay(timeout)
    emit(value)
}
