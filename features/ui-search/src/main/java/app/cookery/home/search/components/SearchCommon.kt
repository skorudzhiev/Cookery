package app.cookery.home.search.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.cookery.common.compose.components.CaptionText

@Composable
fun HeaderAndTextButtonRow(
    @StringRes headerText: Int,
    @StringRes buttonText: Int,
    shouldShowButton: Boolean = true,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        CaptionText(
            text = stringResource(headerText),
            textAlign = TextAlign.Start
        )
        if (shouldShowButton) {
            TextButton(onClick = onClick) {
                CaptionText(
                    text = stringResource(buttonText),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
