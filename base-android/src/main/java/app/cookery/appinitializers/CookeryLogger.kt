package app.cookery.appinitializers

import android.os.Build
import app.cookery.Logger
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Special version of [Timber.DebugTree] which is tailored for Timber being wrapped
 * within another class.
 */
private class CookeryDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, createClassTag(), message, t)
    }

    private fun createClassTag(): String {
        val stackTrace = Throwable().stackTrace
        if (stackTrace.size <= CALL_STACK_INDEX) {
            throw IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?")
        }
        var tag = stackTrace[CALL_STACK_INDEX].className
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        // Tag length limit was removed in API 24.
        return when {
            Build.VERSION.SDK_INT >= 24 || tag.length <= MAX_TAG_LENGTH -> tag
            else -> tag.substring(0, MAX_TAG_LENGTH)
        }
    }

    companion object {
        private const val MAX_TAG_LENGTH = 23
        private const val CALL_STACK_INDEX = 7
        private val ANONYMOUS_CLASS by lazy { Pattern.compile("(\\$\\d+)+$") }
    }
}

class CookeryLogger @Inject constructor() : Logger {

    fun setup(debugMode: Boolean) {
        if (debugMode) {
            Timber.plant(CookeryDebugTree())
        }
        Timber.plant()
    }

    override fun v(message: String, vararg args: Any?) {
        Timber.v(message, *args)
    }

    override fun d(message: String, vararg args: Any?) {
        Timber.d(message, *args)
    }

    override fun d(throwable: Throwable, message: String?, vararg args: Any?) {
        Timber.d(throwable, message, *args)
    }

    override fun i(message: String, vararg args: Any?) {
        Timber.i(message, *args)
    }

    override fun i(t: Throwable, message: String, vararg args: Any?) {
        Timber.i(t, message, *args)
    }

    override fun i(t: Throwable) {
        Timber.i(t)
    }

    override fun e(message: String, vararg args: Any?) {
        Timber.e(message, *args)
    }

    override fun e(t: Throwable, message: String, vararg args: Any?) {
        Timber.e(t, message, *args)
    }

    override fun e(t: Throwable) {
        Timber.e(t)
    }
}
