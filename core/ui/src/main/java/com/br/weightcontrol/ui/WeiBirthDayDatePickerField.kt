package com.br.weightcontrol.ui

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.core.ui.R
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.util.dateValidatorLowerThanToday
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeiBirthDayDatePickerField(
    input: InputWrapper,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier,
    onValueChange: (LocalDate) -> Unit,
) {
    WeiDatePickerField(
        label = { Text(stringResource(id = R.string.birthday_date)) },
        input = input,
        datePickerState = datePickerState,
        modifier = modifier,
        onValueChange = onValueChange,
        dateValidator = dateValidatorLowerThanToday
    )
}
