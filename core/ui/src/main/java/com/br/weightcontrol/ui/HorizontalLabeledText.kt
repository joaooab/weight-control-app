package com.br.weightcontrol.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLabeledText(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = label)
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            fontWeight = FontWeight.Bold
        )
    }
}