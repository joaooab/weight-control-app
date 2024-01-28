package com.br.weightcontrol.ui.text

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private const val DELIMITER = "#"

/**
 * Provide a text with a delimiter for replacing to an icon
 */
@Composable
fun TextWithIcon(@StringRes text: Int, icon: ImageVector, delimiter: String = DELIMITER) {
    val stringResourceWithIcon = stringResourceWithIcon(text, icon, delimiter)
    Text(
        text = stringResourceWithIcon.first,
        inlineContent = stringResourceWithIcon.second,
        modifier = Modifier.padding(top = 16.dp),
    )
}
