package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.weightcontrol.domain.calculateGoalPercentage
import com.br.weightcontrol.goal.R
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.CustomLinearProgressIndicator
import com.br.weightcontrol.ui.VerticalLabeledText

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
            label = R.string.goal_remaining,
            weight = goal.remaining(currentTrack.weight)
        )
        VerticalLabeledText(
            label = R.string.goal_destination,
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