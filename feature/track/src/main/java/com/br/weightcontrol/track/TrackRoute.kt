@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.track

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.core.designsystem.R
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.ui.WeiDatePicker
import com.br.weightcontrol.util.*
import org.koin.androidx.compose.koinViewModel
import com.br.weightcontrol.track.R as trackR

@Composable
internal fun TrackRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var inputDate by rememberSaveable { mutableStateOf(todayAsString()) }
    var inputTrack by rememberSaveable { mutableStateOf("") }
    var datePickerVisible by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = inputDate.toLocalDate().toLong(),
        initialDisplayMode = DisplayMode.Picker,
    )

    when (uiState) {
        is TrackViewModel.UiState.Added -> onBackClick()
        else -> {
            TrackScreen(
                selectedDate = { inputDate },
                selectedTrack = { inputTrack },
                datePickerState = datePickerState,
                datePickerVisible = { datePickerVisible },
                onDateChanged = { inputDate = it },
                onTrackChanged = { inputTrack = getValidatedDecimalNumber(it) },
                onDatePickerVisibleChanged = { datePickerVisible = it },
                onBackClick = onBackClick,
                onSave = viewModel::save,
                modifier = modifier
            )
        }
    }
}

@Composable
internal fun TrackScreen(
    selectedDate: () -> String,
    selectedTrack: () -> String,
    datePickerState: DatePickerState,
    datePickerVisible: () -> Boolean,
    onDateChanged: (String) -> Unit,
    onTrackChanged: (String) -> Unit,
    onDatePickerVisibleChanged: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onSave: (date: String, track: Double) -> Unit,
    modifier: Modifier = Modifier
) {
    if (datePickerVisible()) WeiDatePicker(
        onConfirmButton = { onDateChanged(it.toLocalDate().toString()) },
        onDismissRequest = { onDatePickerVisibleChanged(false) },
        state = datePickerState,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        WeiTopAppBar(
            titleRes = R.string.add_track,
            navigationIcon = { BackNavigationIcon { onBackClick() } }
        )
        Text(
            text = stringResource(trackR.string.track_which_weight),
            modifier = Modifier.padding(top = 16.dp),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedTrack(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onTrackChanged(it) }
        )
        Text(
            text = stringResource(trackR.string.track_which_day),
            modifier = Modifier.padding(top = 16.dp),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedDate(),
            leadingIcon = {
                IconButton(
                    onClick = { onDatePickerVisibleChanged(true) }
                ) {
                    Icon(
                        imageVector = WeiIcons.CalendarBorder,
                        contentDescription = "",
                    )
                }
            },
            onValueChange = { onDateChanged(it) },
            readOnly = true,
        )
        WeiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onSave(selectedDate(), selectedTrack().toDouble()) }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}
