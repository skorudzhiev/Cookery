package com.cookery.util

import app.cookery.presentation.InvokeStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.atomic.AtomicInteger

class ObservableLoadingCounter {
    private val count = AtomicInteger()
    private val loadingState = MutableStateFlow((count.get()))

    val observable: Flow<Boolean>
        get() = loadingState.map { it > 0 }.distinctUntilChanged()

    fun addLoader() {
        loadingState.value = count.incrementAndGet()
    }

    fun removeLoader() {
        loadingState.value = count.decrementAndGet()
    }
}

suspend fun Flow<InvokeStatus>.collectInfo(counter: ObservableLoadingCounter) {
    return onStart { counter.addLoader() }
        .onCompletion { counter.removeLoader() }
        .collect()
}
