@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package br.com.weightcontrol.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.profile.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    var inputName by rememberSaveable { mutableStateOf("") }
    var inputAge by rememberSaveable { mutableStateOf("") }
    var inputHeight by rememberSaveable { mutableStateOf("") }

    ProfileScreen(
        selectedName = { inputName },
        selectedAge = { inputAge },
        selectedHeight = { inputHeight },
        onNameChanged = { inputName = it },
        onAgeChanged = { inputAge = it },
        onHeightChanged = { inputHeight = it },
        onSave = viewModel::save,
        modifier = modifier
    )
}

@Composable
fun ProfileScreen(
    selectedName: () -> String,
    selectedAge: () -> String,
    selectedHeight: () -> String,
    onNameChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSave: (String, String, String) -> Unit
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
            label = { Text(stringResource(id = R.string.name)) }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedHeight(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onHeightChanged(it) },
            label = { Text(stringResource(id = R.string.height)) }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = selectedAge(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onAgeChanged(it) },
            label = { Text(stringResource(id = R.string.age)) }
        )
        Spacer(modifier = Modifier.weight(1f))
        WeiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onSave(selectedName(), selectedAge(), selectedHeight()) }
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
