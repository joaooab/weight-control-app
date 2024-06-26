package com.br.weightcontrol.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.weightcontrol.component.GoalCard
import com.br.weightcontrol.ads.AdmobBanner
import com.br.weightcontrol.bmi.domain.calculateBMI
import com.br.weightcontrol.bmi.domain.format
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.User
import com.br.weightcontrol.model.format
import com.br.weightcontrol.track.component.TrackCard
import com.br.weightcontrol.ui.TrackListResourcePreviewParameterProvider
import com.br.weightcontrol.ui.chart.TrackListChart
import com.br.weightcontrol.util.todayAsString
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeRoute(
    navigateToTrack: () -> Unit,
    navigateToGoal: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val currentTrack by viewModel.currentTrack.collectAsStateWithLifecycle()
    val progress by viewModel.progressState.collectAsStateWithLifecycle()
    val goal by viewModel.goalState.collectAsStateWithLifecycle()
    val history by viewModel.historyState.collectAsStateWithLifecycle()
    HomeScreen(
        user = user,
        currentTrack = currentTrack,
        goal = goal,
        history = history,
        progress = progress,
        modifier = modifier,
        navigateToGoal = navigateToGoal,
        navigateToTrack = navigateToTrack,
    )
}

@Composable
internal fun HomeScreen(
    user: User?,
    currentTrack: Track?,
    history: List<Track>,
    goal: Goal?,
    progress: Progress,
    modifier: Modifier = Modifier,
    navigateToGoal: () -> Unit = {},
    navigateToTrack: () -> Unit = {}
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TrackCard(
                    track = currentTrack,
                    onClickAdd = navigateToTrack,
                )
            }
            item {
                GoalCard(
                    currentTrack = currentTrack,
                    goal = goal,
                    navigateToGoal = navigateToGoal,
                )
            }
            item {
                BodyMassIndexCard(
                    user = user,
                    track = currentTrack,
                )
            }
            item { AdmobBanner() }
            item { TrackListChart(tracks = history) }
            item { TrackProgressCard(progress = progress) }
        }
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

            TrackItem(progress.first, R.string.home_track_first)
            HorizontalDivider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                thickness = 1.dp
            )

            TrackItem(progress.previous, R.string.home_track_previews)
            HorizontalDivider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                thickness = 1.dp
            )

            TrackItem(progress.higher, R.string.home_track_higher)
            HorizontalDivider(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                thickness = 1.dp
            )

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
internal fun BodyMassIndexCard(user: User?, track: Track?, modifier: Modifier = Modifier) {
    if (track == null || user == null) return
    val bmi = calculateBMI(track.weight, user)

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.Speed,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.home_bmi),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = bmi.format(), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = stringResource(bmi.message))
            }
        }
    }
}

@Preview
@Composable
internal fun HomeScreenPreview(
    @PreviewParameter(TrackListResourcePreviewParameterProvider::class)
    trackListResource: List<Track>,
) {
    WeiTheme {
        HomeScreen(
            user = User(),
            currentTrack = Track(),
            goal = Goal(1, 85.0, 80.0, todayAsString()),
            progress = Progress(),
            history = trackListResource
        )
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
        BodyMassIndexCard(User(), Track())
    }
}