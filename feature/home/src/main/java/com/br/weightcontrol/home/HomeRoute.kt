package com.br.weightcontrol.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.format
import com.br.weightcontrol.model.formatBmi
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val progress by viewModel.progressState.collectAsStateWithLifecycle()
    val goal by viewModel.goalState.collectAsStateWithLifecycle()
    HomeScreen(
        progress = progress,
        modifier = modifier,
    )
}

@Composable
internal fun HomeScreen(
    progress: Progress,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(16.dp)) {
        TrackProgressCard(progress)
        Spacer(modifier = Modifier.height(16.dp))
        BodyMassIndexCard(progress.last)
    }
}

@Composable
internal fun TrackProgressCard(
    progress: Progress,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(modifier = Modifier) {
                Icon(
                    imageVector = WeiIcons.Speed,
                    contentDescription = stringResource(R.string.home_speed_icon_description),
                )
                Text(
                    text = stringResource(R.string.home_tracks),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            TrackItem(progress.last, R.string.home_track_current)
            Divider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            TrackItem(progress.previews, R.string.home_track_previews)
            Divider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            TrackItem(progress.higher, R.string.home_track_higher)
            Divider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            TrackItem(progress.lower, R.string.home_track_lower)
        }
    }
}

@Composable
private fun TrackItem(track: Track?, @StringRes label: Int) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(label))
        Text(text = track.format(), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BodyMassIndexCard(track: Track?) {
    Card(
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.Speed,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.home_bmi), fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = track.formatBmi(), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text(text = "Peso normal")
            }
        }
    }
}


@Preview
@Composable
internal fun HomeScreenPreview() {
    WeiTheme {
        HomeScreen(progress = Progress())
    }
}

@Preview
@Composable
internal fun WeightCardPreview() {
    WeiTheme {
        TrackProgressCard(progress = Progress())
    }
}

@Preview
@Composable
internal fun BodyMassIndexCardPreview() {
    WeiTheme {
        BodyMassIndexCard(Track())
    }
}