@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package br.com.weightcontrol.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.profile.R
import com.br.weightcontrol.ui.GenderSelection
import com.br.weightcontrol.ui.WeiDatePickerField
import com.br.weightcontrol.ui.rememberBirthdayDatePickerState
import com.br.weightcontrol.util.dateValidatorLowerThanToday
import com.br.weightcontrol.util.onChangeWithLimit
import com.br.weightcontrol.util.today
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

// TODO: Form validation https://proandroiddev.com/input-validation-in-jetpack-compose-e99c18b44fe3 

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    var inputName by remember { mutableStateOf("") }
    var inputHeight by remember { mutableStateOf("") }
    var inputBirthday by remember { mutableStateOf(today()) }
    var selectedGender by remember { mutableStateOf(Gender.MALE) }
    val datePickerState = rememberBirthdayDatePickerState()

    ProfileScreen(
        selectedName = { inputName },
        selectedHeight = { inputHeight },
        selectedBirthday = { inputBirthday },
        selectedGender = { selectedGender },
        onNameChanged = { inputName = it },
        onBirthdayChanged = { inputBirthday = it },
        onHeightChanged = { inputHeight = onChangeWithLimit(it, 3) },
        onGenderChanged = { selectedGender = it },
        datePickerState = datePickerState,
        onSave = viewModel::save,
        modifier = modifier
    )
}

@Composable
fun ProfileScreen(
    selectedName: () -> String,
    selectedHeight: () -> String,
    selectedBirthday: () -> LocalDate,
    selectedGender: () -> Gender,
    onNameChanged: (String) -> Unit,
    onBirthdayChanged: (LocalDate) -> Unit,
    onHeightChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier,
    onSave: (String, Int, LocalDate) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        WeiTopAppBar(
            titleRes = R.string.profile,
            navigationIcon = { }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedName(),
            onValueChange = { onNameChanged(it) },
            label = { Text(stringResource(id = R.string.name)) },
            isError = selectedName().isBlank()
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedHeight(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            onValueChange = { onHeightChanged(it) },
            label = { Text(stringResource(id = R.string.height)) }
        )
        WeiDatePickerField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedBirthday(),
            datePickerState = datePickerState,
            onValueChange = { onBirthdayChanged(it) },
            dateValidator = dateValidatorLowerThanToday
        )
        Text(
            text = stringResource(id = R.string.gender_select),
            modifier = modifier.padding(top = 16.dp)
        )
        GenderSelection(
            onGenderSelected = { onGenderChanged(it) },
            selectedGender = selectedGender(),
        )
        Spacer(modifier = Modifier.weight(1f))
        WeiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onSave(selectedName(), selectedHeight().toInt(), selectedBirthday()) }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Preview
@Composable
fun ProfileRoutePreview() {
    WeiTheme {
        ProfileRoute()
    }
}
