package app.cookery.domain

import app.cookery.presentation.InvokeStatus
import app.cookery.presentation.InvokeStatus.InvokeError
import app.cookery.presentation.InvokeStatus.InvokeStarted
import app.cookery.presentation.InvokeStatus.InvokeSuccess
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

abstract class Interactor<in P> {
    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> {
        return flow {
            withTimeout(timeoutMs) {
                emit(InvokeStarted)
                doWork(params)
                emit(InvokeSuccess)
            }
        }.catch { throwable ->
            emit(InvokeError(throwable))
        }
    }

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }

    protected abstract suspend fun doWork(params: P)

    suspend fun executeSync(params: P) = doWork(params)
}

abstract class SubjectInteractor<P : Any, T> {
    private val paramState = MutableSharedFlow<P>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val flow: Flow<T> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    fun observe(): Flow<T> = paramState.flatMapLatest { createObservable(it) }

    protected abstract fun createObservable(params: P): Flow<T>
}
