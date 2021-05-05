package app.cookery

interface Logger {

    /** Log a verbose message with optional format args.  */
    fun v(message: String, vararg args: Any?)

    /** Log a debug message with optional format args.  */
    fun d(message: String, vararg args: Any?)

    /** Log a debug exception and a message with optional format args.  */
    fun d(t: Throwable, message: String, vararg args: Any?)

    /** Log an info message with optional format args.  */
    fun i(message: String, vararg args: Any?)

    /** Log an info exception and a message with optional format args.  */
    fun i(t: Throwable, message: String, vararg args: Any?)

    /** Log an info exception.  */
    fun i(t: Throwable)

    /** Log an error message with optional format args.  */
    fun e(message: String, vararg args: Any?)

    /** Log an error exception and a message with optional format args.  */
    fun e(t: Throwable, message: String, vararg args: Any?)

    /** Log an error exception.  */
    fun e(t: Throwable)
}
