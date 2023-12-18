package com.br.weightcontrol.ui.alert

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.designsystem.component.WeiTextButton

@Composable
fun <T> DeleteAlertDialog(
    data: T,
    onConfirmButton: (T) -> Unit,
    description: String,
    onDismissRequest: () -> Unit,
    @StringRes title: Int = R.string.alert
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            WeiTextButton(onClick = { onConfirmButton(data) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            WeiTextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = { Text(text = stringResource(id = title)) },
        text = { Text(text = description) }
    )
}
