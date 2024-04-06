package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.br.weightcontrol.ui.HorizontalLabeledText
import com.br.weightcontrol.util.todayAsString

@Composable
fun GoalCardSettings(
    goal: Goal?,
    currentTrack: Track?,
    onEdit: () -> Unit,
    onDelete: (Goal) -> Unit,
    modifier: Modifier = Modifier
) {
    GoalCard(
        goal = goal,
        currentTrack = currentTrack,
        navigateToGoal = onEdit,
        onProgressGoal = {
            GoalCardProgressSettings(
                goal = goal,
                onEdit = onEdit,
                onDelete = onDelete,
                modifier = modifier
            )
        }
    )
}

@Composable
internal fun GoalCardProgressSettings(
    goal: Goal?,
    onEdit: () -> Unit,
    onDelete: (Goal) -> Unit,
    modifier: Modifier = Modifier
) {
    goal ?: return
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
                    imageVector = WeiIcons.Flag,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.goal),
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = WeiIcons.Edit,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = { onDelete(goal) }) {
                    Icon(
                        imageVector = WeiIcons.Close,
                        contentDescription = null,
                    )
                }
            }
            Column {
                HorizontalLabeledText(
                    label = stringResource(id = R.string.goal_destination_label),
                    value = goal.desire.toString(),
                    modifier = modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
internal fun GoalCardFilledSettingsPreview() {
    WeiTheme {
        GoalCardSettings(
            goal = Goal(start = 85.0, desire = 80.0, createdAt = todayAsString()),
            currentTrack = Track(),
            onEdit = {},
            onDelete = {}
        )
    }
}

