package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.weightcontrol.domain.calculateGoalPercentage
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.goal.R
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.CustomLinearProgressIndicator
import com.br.weightcontrol.ui.VerticalLabeledText

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

@Composable
internal fun GoalEmptyCard(navigateToGoal: () -> Unit) {
    Text(
        text = stringResource(R.string.goal_empty),
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
internal fun GoalFilledCard(goal: Goal, currentTrack: Track?) {
    if (currentTrack == null) return
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        VerticalLabeledText(
            label = R.string.goal_begin,
            weight = goal.start
        )
        VerticalLabeledText(
            label = R.string.goal_remaning,
            weight = goal.remaining(currentTrack.weight)
        )
        VerticalLabeledText(
            label = R.string.goal_end,
            weight = goal.desire
        )
    }
    CustomLinearProgressIndicator(
        targetProgress = calculateGoalPercentage(goal, currentTrack),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 56.dp)
            .padding(top = 16.dp)
    )
}