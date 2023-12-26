package br.com.weightcontrol.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.util.today
import com.br.weightcontrol.util.todayAsString

@Composable
fun GoalCard(
    goal: Goal?,
    currentTrack: Track?,
    navigateToGoal: () -> Unit,
    modifier: Modifier = Modifier,
    onEmptyGoal: @Composable () -> Unit = { GoalEmptyCard(navigateToGoal) },
    onCompletedGoal: @Composable () -> Unit = { GoalCompletionCard() },
    onProgressGoal: @Composable () -> Unit = { GoalProgressCard(goal, currentTrack) }
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        when {
            goal == null -> onEmptyGoal()
            goal.isFinished() -> onCompletedGoal()
            else -> onProgressGoal()
        }
    }
}


@Preview
@Composable
fun GoalEmptyCardPreview() {
    WeiTheme {
        GoalCard(
            goal = null,
            currentTrack = null,
            navigateToGoal = {}
        )
    }
}

@Preview
@Composable
fun GoalCompletedCardPreview() {
    WeiTheme {
        GoalCard(
            goal = Goal(
                start = 85.0,
                desire = 80.0,
                createdAt = todayAsString(),
                completedAt = todayAsString()
            ),
            currentTrack = Track(weight = 84.0, createdAt = today()),
            navigateToGoal = {}
        )
    }
}

@Preview
@Composable
fun GoalFilledCardPreview() {
    WeiTheme {
        GoalCard(
            goal = Goal(start = 85.0, desire = 80.0, createdAt = todayAsString()),
            currentTrack = Track(weight = 84.0, createdAt = today()),
            navigateToGoal = {}
        )
    }
}