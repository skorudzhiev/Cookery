package app.cookery

import android.content.Context
import app.cookery.presentation.CookeryError
import app.cookery.presentation.CookeryError.BadRequest
import app.cookery.presentation.CookeryError.Expired
import app.cookery.presentation.CookeryError.Forbidden
import app.cookery.presentation.CookeryError.IllegalArgument
import app.cookery.presentation.CookeryError.NetworkOffline
import app.cookery.presentation.CookeryError.NetworkTimeout
import app.cookery.presentation.CookeryError.Unauthorized
import com.skorudzhiev.cookery.base.android.R
import javax.inject.Inject

class ErrorMapper @Inject constructor(private val context: Context) {

    fun mapError(error: CookeryError?): String? {
        if (error == null) return null
        return when (error) {
            is NetworkOffline -> context.getString(R.string.error_network_offline)
            is NetworkTimeout -> context.getString(R.string.error_network_timeout)
            is IllegalArgument -> context.getString(R.string.error_illegal_argument)
            is BadRequest -> context.getString(R.string.error_bad_request)
            is Forbidden -> context.getString(R.string.error_forbidden)
            is Unauthorized -> context.getString(R.string.error_unauthorized)
            is Expired -> context.getString(R.string.error_expired)
            else -> {
                context.getString(R.string.error_unknown)
            }
        }
    }
}
