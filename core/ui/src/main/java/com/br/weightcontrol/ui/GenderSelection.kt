package com.br.weightcontrol.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Gender

@Composable
fun GenderSelection(
    onGenderSelected: (Gender) -> Unit,
    gender: Gender = Gender.MALE
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GenderIcon(
            gender = Gender.MALE,
            selected = gender == Gender.MALE,
            onClick = { onGenderSelected(Gender.MALE) }
        )

        GenderIcon(
            gender = Gender.FEMALE,
            selected = gender == Gender.FEMALE,
            onClick = { onGenderSelected(Gender.FEMALE) }
        )
    }
}

@Preview
@Composable
fun GenderSelectionPreview() {
    WeiTheme {
        GenderSelection(onGenderSelected = {}, gender = Gender.MALE)
    }
}