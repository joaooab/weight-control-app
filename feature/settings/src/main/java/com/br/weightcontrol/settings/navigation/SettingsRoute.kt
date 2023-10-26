package com.br.weightcontrol.settings.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.icon.WeiIcons
import com.br.weightcontrol.model.Gender
import com.br.weightcontrol.model.User
import com.br.weightcontrol.settings.R
import com.br.weightcontrol.util.calculateAge
import com.br.weightcontrol.util.defaultBirthday
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SettingsRoute(
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val user by viewModel.user.collectAsStateWithLifecycle()

    SettingsScreen(
        user = user,
        navigateToProfile = navigateToProfile,
        modifier = modifier
    )
}

@Composable
private fun SettingsScreen(
    user: User,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = WeiIcons.Person,
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        text = user.name, style = MaterialTheme.typography.titleMedium,
                    )
                    IconButton(onClick = { navigateToProfile() }) {
                        Icon(
                            imageVector = WeiIcons.Edit,
                            contentDescription = null,
                        )
                    }
                }

                LineText(
                    label = stringResource(id = R.string.label_age),
                    value = calculateAge(user.birthday).toString(),
                    modifier = modifier
                )
                LineText(
                    label = stringResource(id = R.string.label_height),
                    value = user.height.toString()
                )
                LineText(
                    label = stringResource(id = R.string.label_gender),
                    value = user.gender.name
                )
            }
        }
    }
}

@Composable
fun LineText(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(top = 8.dp)) {
        Text(text = label)
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        user = User(
            name = "Joao Vitor",
            height = 180,
            birthday = defaultBirthday(),
            gender = Gender.MALE
        ),
        navigateToProfile = {}
    )
}