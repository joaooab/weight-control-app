package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.goal.R
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.util.today
import com.br.weightcontrol.util.todayAsString

@Composable
fun GoalCard(
    goal: Goal?,
    currentTrack: Track?,
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
                    text = stringResource(R.string.goal),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            if (goal == null) GoalEmptyCard(navigateToGoal)
            else GoalFilledCard(goal, currentTrack)
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
fun GoalFilledCardPreview() {
    WeiTheme {
        GoalCard(
            goal = Goal(start = 85.0, desire = 80.0, createdAt = todayAsString()),
            currentTrack = Track(weight = 84.0, createdAt = today()),
            navigateToGoal = {}
        )
    }
}