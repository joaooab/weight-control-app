package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import br.com.weightcontrol.domain.calculateGoalPercentage
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.goal.R
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.CustomLinearProgressIndicator
import com.br.weightcontrol.ui.VerticalLabeledText
import com.br.weightcontrol.util.calculateWithScale

@Composable
internal fun GoalProgressCard(
    goal: Goal?,
    currentTrack: Track?,
    modifier: Modifier = Modifier
) {
    goal ?: return
    currentTrack ?: return
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
                    text = stringResource(R.string.goal),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
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
                    weight = calculateWithScale { goal.remaining(currentTrack.weight) }
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
    }
}