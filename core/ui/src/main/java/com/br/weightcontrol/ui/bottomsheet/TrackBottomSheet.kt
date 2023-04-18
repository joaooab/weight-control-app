@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.ui.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.core.designsystem.R
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.util.todayAsString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TrackBottomSheet(
    lastTrackValue: Double = 75.0,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    onSave: () -> Unit = {}
) {
    val inputDate: MutableState<String> = rememberSaveable { mutableStateOf(todayAsString()) }
    val inputTrack: MutableState<Double> = rememberSaveable { mutableStateOf(lastTrackValue) }

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = modifier,
        onDismissRequest = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        ) {
            Text(
                text = stringResource(R.string.add_track),
                style = MaterialTheme.typography.titleMedium,
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                value = inputDate.value,
                trailingIcon = {
                    Icon(
                        imageVector = WeiIcons.CalendarBorder,
                        contentDescription = "",
                    )
                },
                onValueChange = {
                    inputDate.value = it
                }
            )
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp),
//                value = inputTrack.value,
//                trailingIcon = {
//                    Icon(
//                        imageVector = WeiIcons.Fitness,
//                        contentDescription = "",
//                    )
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                onValueChange = {
//                    inputTrack.value = 85.1
//                }
//            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    onClick = onSave
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            }

        }
    }
}

@Preview
@Composable
fun TrackBottomSheetPreview() {
    WeiTheme {
        TrackBottomSheet(80.0)
    }
}