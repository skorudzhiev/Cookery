package com.cookery.common.compose.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skorudzhiev.cookery.common.ui.compose.R

@Composable
fun ExpandingText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.body2,
    expandable: Boolean = true,
    collapsedMaxLines: Int = 4,
    expandedMaxLines: Int = Int.MAX_VALUE,
) {
    var canTextExpand by remember(text) { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    var seeMore by remember { mutableStateOf(true) }

    Text(
        text = text,
        style = textStyle,
        overflow = TextOverflow.Ellipsis,
        maxLines = if (seeMore) collapsedMaxLines else expandedMaxLines,
        modifier = Modifier
            .animateContentSize(animationSpec = spring())
            .then(modifier),
        onTextLayout = {
            if (!expanded) {
                canTextExpand = it.hasVisualOverflow
            }
        }
    )
    val textButton = if (seeMore) {
        stringResource(id = R.string.see_more)
    } else {
        stringResource(id = R.string.see_less)
    }

    if (expandable) {
        Text(
            text = textButton,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .heightIn(20.dp)
                .fillMaxWidth()
                .padding(top = 15.dp)
                .clickable {
                    seeMore = !seeMore
                }
        )
    }
}

@Composable
fun CaptionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption
    )
}
