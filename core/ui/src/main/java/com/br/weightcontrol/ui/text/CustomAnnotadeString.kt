package com.br.weightcontrol.ui.text

import androidx.annotation.StringRes
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp

typealias EntryInlineTextContent = Map<String, InlineTextContent>

private const val ICON_ID = "inlineContent"
private const val ICON_PLACE_HOLDER = "[icon]"

@Composable
fun stringResourceWithIcon(
    @StringRes text: Int,
    icon: ImageVector,
    delimiter: String
): Pair<AnnotatedString, EntryInlineTextContent> {
    val stringResources = stringResource(id = text).split(delimiter)
    val inlineContent = createInlineContent(icon)
    val annotatedString = buildAnnotatedString {
        stringResources.getOrNull(0)?.let(::append)
        appendInlineContent(ICON_ID, ICON_PLACE_HOLDER)
        stringResources.getOrNull(1)?.let(::append)
    }

    return Pair(annotatedString, inlineContent)
}

@Composable
private fun createInlineContent(icon: ImageVector) = mapOf(
    Pair(
        ICON_ID,
        InlineTextContent(
            Placeholder(
                width = 20.sp,
                height = 20.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(icon, null)
        }
    )
)
