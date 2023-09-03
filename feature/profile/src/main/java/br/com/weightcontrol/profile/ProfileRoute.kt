@file:OptIn(
    ExperimentalMaterial3Api::class
)

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.profile.R
import com.br.weightcontrol.ui.GenderSelection
import com.br.weightcontrol.ui.WeiDatePickerField
import com.br.weightcontrol.ui.input.InputHandler
import com.br.weightcontrol.ui.rememberBirthdayDatePickerState
import com.br.weightcontrol.util.dateValidatorLowerThanToday
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val height by viewModel.height.collectAsStateWithLifecycle()
    val birthday by viewModel.birthday.collectAsStateWithLifecycle()
    val gender by viewModel.gender.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()
    val datePickerState = rememberBirthdayDatePickerState()

    ProfileScreen(
        name = name,
        height = height,
        birthday = birthday,
        gender = gender,
        onNameChanged = viewModel::onNameEntered,
        onHeightChanged = viewModel::onHeightEntered,
        onBirthdayChanged = viewModel::onBirthdayEntered,
        onGenderChanged = viewModel::onGenderEntered,
        areInputsValid = areInputsValid,
        datePickerState = datePickerState,
        onSave = viewModel::save,
        modifier = modifier
    )
}

@Composable
fun ProfileScreen(
    name: InputHandler,
    height: InputHandler,
    birthday: InputHandler,
    gender: InputHandler,
    onNameChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onBirthdayChanged: (LocalDate) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    areInputsValid: Boolean,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier,
    onSave: () -> Unit
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
            value = name.input,
            onValueChange = { onNameChanged(it) },
            label = { Text(stringResource(id = R.string.name)) },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = height.input,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            onValueChange = { onHeightChanged(it) },
            label = { Text(stringResource(id = R.string.height)) }
        )
        WeiDatePickerField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = birthday.input,
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
            gender = Gender.valueOf(gender.input),
        )
        Spacer(modifier = Modifier.weight(1f))
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

@Preview
@Composable
fun ProfileRoutePreview() {
    WeiTheme {
        ProfileRoute()
    }
}