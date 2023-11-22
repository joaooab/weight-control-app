@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.weightcontrol

import GoalViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.bmi.domain.WeightRecommendation
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.goal.R
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Track
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.util.*
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun GoalRoute(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: GoalViewModel = koinViewModel(),
) {
    val weight by viewModel.weight.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()
    val currentTrack by viewModel.currentTrack.collectAsStateWithLifecycle()
    val recommendation by viewModel.recommendation.collectAsStateWithLifecycle()
    val saveState by viewModel.saveActionState

    GoalScreen(
        weight = weight,
        onWeightChanged = viewModel::onWeightEntered,
        areInputsValid = areInputsValid,
        currentTrack = currentTrack,
        recommendation = recommendation,
        onClose = onClose,
        onSave = viewModel::save,
        saveState = saveState,
        onShowSnackBar = onShowSnackBar,
        onDismissSnackBar = viewModel::clearSaveAction,
        modifier = modifier
    )
}

@Composable
internal fun GoalScreen(
    weight: InputWrapper,
    onWeightChanged: (String) -> Unit,
    areInputsValid: Boolean,
    currentTrack: Track?,
    recommendation: WeightRecommendation?,
    onClose: () -> Unit,
    onSave: () -> Unit,
    saveState: ActionState,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    onDismissSnackBar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val errorCreateTrackMessage = ""

    LaunchedEffect(saveState) {
        when (saveState) {
            is ActionState.Success -> onClose()
            is ActionState.Failure -> {
                onShowSnackBar(errorCreateTrackMessage, null)
                onDismissSnackBar()
            }

            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        WeiTopAppBar(
            titleRes = R.string.add_track,
            navigationIcon = { BackNavigationIcon { onClose() } }
        )
        CurrentTrackText(
            currentTrack = currentTrack,
            modifier = Modifier.padding(top = 32.dp)
        )
        RecommendationText(
            recommendation = recommendation,
            modifier = Modifier.padding(top = 8.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = weight.input,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = { onWeightChanged(it) },
            label = { Text(stringResource(id = R.string.goal_target_input)) },
            isError = weight.hasError(),
            supportingText = { weight.errorId?.let { Text(stringResource(id = it)) } }
        )
        WeiButton(
            enabled = areInputsValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onSave() }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Composable
fun CurrentTrackText(currentTrack: Track?, modifier: Modifier) {
    currentTrack?.let {
        Text(
            text = stringResource(
                id = R.string.goal_current_track,
                it.weight
            ),
            modifier = modifier
        )
    }
}

@Composable
private fun RecommendationText(recommendation: WeightRecommendation?, modifier: Modifier) {
    recommendation?.let {
        Text(
            text = stringResource(
                id = R.string.goal_recommendation,
                it.minWeight,
                it.maxWeight
            ),
            modifier = modifier
        )
    }
}
