package com.br.weightcontrol.history

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.designsystem.component.WeiTextButton
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.format

@Composable
fun DeleteAlertDialog(
    track: Track,
    onConfirmButton: (Track) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            WeiTextButton(onClick = { onConfirmButton(track) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            WeiTextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = { Text(text = stringResource(id = R.string.alert)) },
        text = {
            Text(
                text = stringResource(
                    id = R.string.history_delete_track_description,
                    track.format(),
                    track.createdAt.toString()
                )
            )
        }
    )
}
