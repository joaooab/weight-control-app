package com.br.weightcontrol.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.core.designsystem.R
import com.br.weightcontrol.designsystem.component.WeiTextButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeiDatePicker(
    onConfirmButton: (Long) -> Unit,
    onDismissRequest: () -> Unit,
    state: DatePickerState,
    modifier: Modifier = Modifier,
) {
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            WeiTextButton(onClick = {
                state.selectedDateMillis?.let(onConfirmButton)
                onDismissRequest()
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            WeiTextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = state,
            title = null,
            headline = null,
            showModeToggle = false,
        )
    }
}
