package com.br.weightcontrol.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import br.com.weightcontrol.domain.calculatePercentage
import com.br.weightcontrol.bmi.domain.calculateBMI
import com.br.weightcontrol.bmi.domain.format
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.model.User
import com.br.weightcontrol.model.format
import com.br.weightcontrol.ui.CustomLinearProgressIndicator
import com.br.weightcontrol.ui.TrackListResourcePreviewParameterProvider
import com.br.weightcontrol.ui.chart.TrackListChart
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeRoute(
    navigateToGoal: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val progress by viewModel.progressState.collectAsStateWithLifecycle()
    val goal by viewModel.goalState.collectAsStateWithLifecycle()
    val history by viewModel.historyState.collectAsStateWithLifecycle()
    HomeScreen(
        user = user,
        progress = progress,
        history = history,
        goal = goal,
        modifier = modifier,
        navigateToGoal = navigateToGoal,
    )
}

@Composable
internal fun HomeScreen(
    user: User?,
    progress: Progress,
    history: List<Track>,
    goal: Goal?,
    modifier: Modifier = Modifier,
    navigateToGoal: () -> Unit = {}
) {
    Column(modifier.padding(16.dp)) {
        LazyColumn {
            item {
                TrackProgressCard(progress)
            }
            item {
                user?.let {
                    BodyMassIndexCard(
                        user = user,
                        track = progress.current,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
            item {
                TrackListChart(
                    tracks = history,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            item {
                GoalCard(
                    goal = goal,
                    navigateToGoal = navigateToGoal,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
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

            TrackItem(progress.current, R.string.home_track_current)
            Divider(thickness = 1.dp, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            TrackItem(progress.previous, R.string.home_track_previews)
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
internal fun BodyMassIndexCard(user: User, track: Track?, modifier: Modifier = Modifier) {
    if (track == null) return
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

@Composable
internal fun GoalCard(
    goal: Goal?,
    navigateToGoal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.Flag,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.home_goal),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            if (goal == null) GoalEmptyCard(navigateToGoal)
            else GoalFilledCard(goal)
        }
    }
}

@Composable
internal fun GoalEmptyCard(navigateToGoal: () -> Unit) {
    Text(
        text = stringResource(R.string.home_empty_goal),
        modifier = Modifier.padding(top = 16.dp),
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Button(
            onClick = { navigateToGoal() },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}


@Composable
internal fun GoalFilledCard(goal: Goal) {
    CustomLinearProgressIndicator(
        progress = calculatePercentage(goal),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(bottom = 32.dp)
    )
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
            progress = Progress(),
            history = trackListResource,
            goal = null
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