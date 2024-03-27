package com.br.weightcontrol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.core.designsystem.R as desingSystemR
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Gender

@Composable
fun GenderIcon(
    gender: Gender,
    selected: Boolean,
    onClick: () -> Unit
) {
    val color =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
    val image = if (gender == Gender.MALE) R.drawable.ic_male else R.drawable.ic_female

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .toggleable(value = selected, onValueChange = { onClick() })
                .clip(MaterialTheme.shapes.medium)
                .background(color = color)
                .clickable(onClick = onClick)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = gender.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
                colorFilter = if (selected) ColorFilter.tint(Color.White) else null
            )
        }
        Text(text = stringResource(id = gender.description()))
    }
}

private fun Gender.description() = when (this) {
    Gender.MALE -> desingSystemR.string.male
    else -> desingSystemR.string.female
}

@Preview
@Composable
fun GenderIconPreview() {
    WeiTheme {
        GenderIcon(gender = Gender.MALE, selected = true, onClick = {})
    }
}