package br.com.weightcontrol.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.model.ActionState
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.profile.R
import com.br.weightcontrol.core.designsystem.R as designSystemR
import com.br.weightcontrol.ui.GenderSelection
import com.br.weightcontrol.ui.WeiBirthDayDatePickerField
import com.br.weightcontrol.ui.input.InputWrapper
import com.br.weightcontrol.ui.rememberBirthdayDatePickerState
import kotlinx.datetime.LocalDate
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileRoute(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val height by viewModel.height.collectAsStateWithLifecycle()
    val birthday by viewModel.birthday.collectAsStateWithLifecycle()
    val gender by viewModel.gender.collectAsStateWithLifecycle()
    val areInputsValid by viewModel.areInputsValid.collectAsStateWithLifecycle()
    val datePickerState = rememberBirthdayDatePickerState()
    val saveState by viewModel.saveActionState

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
        saveState = saveState,
        onClose = onClose,
        onShowSnackBar = onShowSnackBar,
        onDismissSnackBar = viewModel::clearSaveAction,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    name: InputWrapper,
    height: InputWrapper,
    birthday: InputWrapper,
    gender: InputWrapper,
    onNameChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onBirthdayChanged: (LocalDate) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    areInputsValid: Boolean,
    datePickerState: DatePickerState,
    onSave: () -> Unit,
    saveState: ActionState,
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    onDismissSnackBar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val errorCreateProfileMessage = stringResource(id = R.string.error_create_profile)

    LaunchedEffect(saveState) {
        when (saveState) {
            is ActionState.Success -> onClose()
            is ActionState.Failure -> {
                onShowSnackBar(errorCreateProfileMessage, null)
                onDismissSnackBar()
            }

            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .navigationBarsPadding(),
    ) {
        WeiTopAppBar(
            titleRes = designSystemR.string.profile,
            navigationIcon = { BackNavigationIcon { onClose() } }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = name.input,
            onValueChange = { onNameChanged(it) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            label = { Text(stringResource(id = R.string.name)) },
            isError = name.hasError(),
            supportingText = { name.errorId?.let { Text(stringResource(id = it)) } }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = height.input,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = { onHeightChanged(it) },
            label = { Text(stringResource(id = R.string.height)) },
            isError = height.hasError(),
            supportingText = { height.errorId?.let { Text(stringResource(id = it)) } }
        )
        WeiBirthDayDatePickerField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            input = birthday,
            datePickerState = datePickerState,
            onValueChange = { onBirthdayChanged(it) },
        )
        Text(
            text = stringResource(id = designSystemR.string.gender_select),
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
                .padding(bottom = 24.dp),
            onClick = { onSave() }
        ) {
            Text(text = stringResource(id = designSystemR.string.save))
        }
    }
}

@Preview
@Composable
fun ProfileRoutePreview() {
    WeiTheme {
        ProfileRoute(
            onClose = {},
            onShowSnackBar = { _, _ -> false }
        )
    }
}
