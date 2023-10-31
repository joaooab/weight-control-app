package com.br.weightcontrol.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.history.domain.model.DeleteDialogState
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.format
import com.br.weightcontrol.util.format
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val tracks by viewModel.tracks.collectAsStateWithLifecycle(emptyList())
    val deleteDialogState by viewModel.showDeleteDialog.collectAsStateWithLifecycle()
    when (val state = deleteDialogState) {
        is DeleteDialogState.Open -> DeleteAlertDialog(
            track = state.track,
            onConfirmButton = viewModel::onDeleteConfirm,
            onDismissRequest = viewModel::onDeleteDismiss
        )
        else -> Unit
    }

    HistoryScreen(
        tracks = tracks,
        onDelete = viewModel::onDelete,
        modifier = modifier,
    )
}


@Composable
fun HistoryScreen(
    tracks: List<Track>,
    onDelete: (Track) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        tracks.forEach { track ->
            item(key = track.id) {
                TrackItem(track, onDelete)
            }
        }
    }
}

@Composable
fun TrackItem(
    track: Track,
    onDelete: (Track) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProvideTextStyle(
                content = {
                    Text(
                        text = track.createdAt.format(),
                    )
                    Text(
                        text = track.format(),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                value = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            IconButton(onClick = { onDelete(track) }) {
                Icon(
                    imageVector = WeiIcons.Close,
                    contentDescription = "",
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    WeiTheme {
        HistoryScreen(
            tracks = listOf(
                Track(id = 0),
                Track(id = 1),
                Track(id = 2)
            ),
            onDelete = {}
        )
    }
}


@Preview
@Composable
fun TrackItem() {
    WeiTheme {
        TrackItem(
            track = Track(),
            onDelete = {}
        )
    }
}
