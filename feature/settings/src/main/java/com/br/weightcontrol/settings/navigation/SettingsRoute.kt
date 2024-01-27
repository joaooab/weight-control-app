package com.br.weightcontrol.settings.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.weightcontrol.component.GoalCardSettings
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.Goal
import com.br.weightcontrol.model.User
import com.br.weightcontrol.settings.R
import com.br.weightcontrol.ui.HorizontalLabeledText
import com.br.weightcontrol.ui.alert.DeleteAlertDialog
import com.br.weightcontrol.ui.domain.DeleteDialogState
import com.br.weightcontrol.util.calculateAge
import com.br.weightcontrol.util.defaultBirthday
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SettingsRoute(
    navigateToProfile: () -> Unit,
    navigateToGoal: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val goal by viewModel.goal.collectAsStateWithLifecycle()
    val deleteDialogState by viewModel.showDeleteDialog.collectAsStateWithLifecycle()

    when (val state = deleteDialogState) {
        is DeleteDialogState.Open -> DeleteAlertDialog(
            data = state.data,
            description = stringResource(id = R.string.alert_delete_goal),
            onConfirmButton = viewModel::onDeleteConfirm,
            onDismissRequest = viewModel::onDeleteDismiss
        )

        else -> Unit
    }

    SettingsScreen(
        user = user,
        goal = goal,
        navigateToProfile = navigateToProfile,
        navigateToGoal = navigateToGoal,
        onDelete = viewModel::onDelete,
        modifier = modifier
    )
}

@Composable
private fun SettingsScreen(
    user: User,
    goal: Goal?,
    navigateToProfile: () -> Unit,
    navigateToGoal: () -> Unit,
    onDelete: (Goal) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        CardPersonSettings(
            user = user,
            navigateToProfile = navigateToProfile,
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        GoalCardSettings(
            goal = goal,
            onEdit = navigateToGoal,
            onDelete = onDelete
        )
    }
}

@Composable
private fun CardPersonSettings(
    user: User,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
                .padding(bottom = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = WeiIcons.Person,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    text = user.name, style = MaterialTheme.typography.titleMedium,
                )
                IconButton(onClick = { navigateToProfile() }) {
                    Icon(
                        imageVector = WeiIcons.Edit,
                        contentDescription = null,
                    )
                }
            }

            HorizontalLabeledText(
                label = stringResource(id = R.string.label_age),
                value = calculateAge(user.birthday).toString(),
                modifier = modifier.padding(top = 8.dp)
            )
            HorizontalLabeledText(
                label = stringResource(id = R.string.label_height),
                value = user.height.toString(),
                modifier = modifier.padding(top = 8.dp)
            )
            HorizontalLabeledText(
                label = stringResource(id = R.string.label_gender),
                value = user.gender.name,
                modifier = modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        user = User(
            name = "Joao Vitor",
            height = 180,
            birthday = defaultBirthday(),
            gender = Gender.MALE
        ),
        goal = null,
        navigateToProfile = {},
        navigateToGoal = {},
        onDelete = {},
    )
}