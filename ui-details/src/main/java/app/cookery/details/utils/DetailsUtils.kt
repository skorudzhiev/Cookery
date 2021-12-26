package app.cookery.details.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun openYoutubeLink(
    youtubeID: String,
    context: Context
) {
    val id = youtubeID.substringAfter("watch?v=")
    val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$id"))
    try {
        context.startActivity(intentApp)
    } catch (ex: ActivityNotFoundException) {
        context.startActivity(intentBrowser)
    }
}
