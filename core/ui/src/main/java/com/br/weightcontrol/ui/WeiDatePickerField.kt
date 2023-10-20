package com.br.weightcontrol.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.util.toLocalDate
import com.br.weightcontrol.util.today
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeiDatePickerField(
    label: @Composable (() -> Unit),
    input: InputWrapper,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier,
    onValueChange: (LocalDate) -> Unit,
    dateValidator: (Long) -> Boolean = { true },
) {
    val interactionSource = remember { MutableInteractionSource() }
    var datePickerVisible by remember { mutableStateOf(false) }

    if (datePickerVisible) WeiDatePicker(
        onConfirmButton = { onValueChange(it.toLocalDate()) },
        onDismissRequest = { datePickerVisible = false },
        state = datePickerState,
        dateValidator = dateValidator
    )

    OutlinedTextField(
        value = input.input,
        enabled = false,
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = WeiIcons.CalendarBorder,
                contentDescription = "",
            )
        },
        label = label,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { datePickerVisible = true }
            ),
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        isError = input.hasError(),
        supportingText = { input.errorId?.let { Text(stringResource(id = it)) } }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBirthdayDatePickerState(
    initialDisplayMode: DisplayMode = DisplayMode.Picker
) = rememberDatePickerState(
    initialDisplayMode = initialDisplayMode,
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ReadonlyTextFieldPreview() {
    WeiTheme {
        WeiDatePickerField(
            label = { Text(stringResource(id = R.string.birthday_date)) },
            input = InputWrapper(today().toString()),
            datePickerState = rememberBirthdayDatePickerState(),
            onValueChange = {},
        )
    }
}

