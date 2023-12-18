package br.com.weightcontrol.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.goal.R

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