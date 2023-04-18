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
import com.br.weightcontrol.track.R as trackR
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.util.getValidatedDecimalNumber
import com.br.weightcontrol.util.todayAsString
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TrackRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var inputDate by rememberSaveable { mutableStateOf(todayAsString()) }
    var inputTrack by rememberSaveable { mutableStateOf("") }

    when (uiState) {
        is TrackViewModel.UiState.Added -> onBackClick()
        else -> {
            TrackScreen(
                currentDate = { inputDate },
                currentTrack = { inputTrack },
                onDateChanged = { inputDate = it },
                onTrackChanged = { inputTrack = getValidatedDecimalNumber(it) },
                onBackClick = onBackClick,
                modifier = modifier,
                onSave = viewModel::save
            )
        }
    }
}

@Composable
internal fun TrackScreen(
    currentDate: () -> String,
    currentTrack: () -> String,
    onDateChanged: (String) -> Unit,
    onTrackChanged: (String) -> Unit,
    onBackClick: () -> Unit,
    onSave: (date: String, track: Double) -> Unit,
    modifier: Modifier = Modifier
) {
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
            modifier = Modifier
                .padding(top = 16.dp),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = currentTrack(),
            trailingIcon = {
                Icon(
                    imageVector = WeiIcons.Fitness,
                    contentDescription = "",
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onTrackChanged(it) }
        )
        Text(
            text = stringResource(trackR.string.track_which_day),
            modifier = Modifier
                .padding(top = 16.dp),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = currentDate(),
            trailingIcon = {
                Icon(
                    imageVector = WeiIcons.CalendarBorder,
                    contentDescription = "",
                )
            },
            onValueChange = { onDateChanged(it) }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onSave(currentDate(), currentTrack().toDouble()) }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}
