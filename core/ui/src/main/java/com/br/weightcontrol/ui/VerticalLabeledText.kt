package com.br.weightcontrol.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.designsystem.theme.WeiTheme

@Composable
fun VerticalLabeledText(
    @StringRes label: Int,
    weight: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(label),
        )
        Text(
            text = "$weight Kg",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun VerticalLabeledTextPreview() {
    WeiTheme {
        VerticalLabeledText(
            label = R.string.add,
            weight = 80.5,
        )
    }
}