package app.cookery.presentation

abstract class CookeryError {

    data object NetworkOffline : CookeryError()

    data object NetworkTimeout : CookeryError()

    data object IllegalArgument : CookeryError()

    data object BadRequest : CookeryError()

    data object Forbidden : CookeryError()

    data object Unauthorized : CookeryError()

    data object Expired : CookeryError()

    data object Unknown : CookeryError()
}
