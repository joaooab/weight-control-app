@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.track

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
import com.br.weightcontrol.core.designsystem.R
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.ui.WeiDatePickerField
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.ui.rememberDefaultDatePickerState
import com.br.weightcontrol.util.*
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel
import com.br.weightcontrol.track.R as trackR

@Composable
internal fun TrackRoute(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel(),
) {
    val weight by viewModel.weight.collectAsStateWithLifecycle()
    val date by viewModel.date.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()
    val datePickerState = rememberDefaultDatePickerState()
    val saveState  by viewModel.saveActionState

    TrackScreen(
        weight = weight,
        date = date,
        onWeightChanged = viewModel::onWeightEntered,
        onDateChanged = viewModel::onDateEntered,
        areInputsValid = areInputsValid,
        datePickerState = datePickerState,
        onClose = onClose,
        onSave = viewModel::save,
        saveState = saveState,
        onShowSnackBar = onShowSnackBar,
        onDismissSnackBar = viewModel::clearSaveAction,
        modifier = modifier
    )
}

@Composable
internal fun TrackScreen(
    weight: InputWrapper,
    date: InputWrapper,
    onWeightChanged: (String) -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    areInputsValid: Boolean,
    datePickerState: DatePickerState,
    onClose: () -> Unit,
    onSave: () -> Unit,
    saveState: ActionState,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    onDismissSnackBar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val errorCreateTrackMessage = stringResource(id = R.string.generic_error)

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
            label = { Text(stringResource(id = trackR.string.track_which_weight)) },
            isError = weight.hasError(),
            supportingText = { weight.errorId?.let { Text(stringResource(id = it)) } }
        )
        WeiDatePickerField(
            label = { Text(text = stringResource(id = trackR.string.track_which_day))},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            input = date,
            datePickerState = datePickerState,
            onValueChange = { onDateChanged(it) },
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
