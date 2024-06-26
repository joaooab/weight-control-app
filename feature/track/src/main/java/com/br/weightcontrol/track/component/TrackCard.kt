package com.br.weightcontrol.track.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.core.designsystem.R
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.formatWeight
import com.br.weightcontrol.ui.text.TextWithIcon
import com.br.weightcontrol.util.format
import com.br.weightcontrol.util.today
import com.br.weightcontrol.track.R as trackR

@Composable
fun TrackCard(
    track: Track?,
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (track) {
        null -> EmptyTrackCard(
            onClickAdd = onClickAdd,
            modifier = modifier
        )

        else -> CurrentTrackCard(
            track = track,
            onClickAdd = onClickAdd,
            modifier = modifier
        )
    }
}

@Composable
private fun EmptyTrackCard(
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.Flag,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.current_weight),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onClickAdd) {
                    Icon(
                        imageVector = WeiIcons.Add,
                        contentDescription = null,
                    )
                }
            }
            TextWithIcon(
                text = trackR.string.track_empty,
                icon = WeiIcons.Add,
            )
        }
    }
}

@Composable
private fun CurrentTrackCard(
    track: Track,
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0f) }
    val progressAnimation: Float by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), label = ""
    )
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .padding(bottom = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.MonitorWeight,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.current_weight),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onClickAdd) {
                    Icon(
                        imageVector = WeiIcons.Add,
                        contentDescription = null,
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = progressAnimation.formatWeight(),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = track.createdAt.format(),
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
    LaunchedEffect(track) {
        progress = track.weight.toFloat()
    }
}

@Preview
@Composable
fun EmptyTrackCardPreview() {
    WeiTheme {
        EmptyTrackCard(onClickAdd = { })
    }
}

@Preview
@Composable
fun CurrentTrackCardPreview() {
    WeiTheme {
        CurrentTrackCard(
            track = Track(1, 80.0, today()),
            onClickAdd = {}
        )
    }
}