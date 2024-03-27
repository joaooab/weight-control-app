package com.br.weightcontrol.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.designsystem.icon.WeiIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeiTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent,
    ),
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        colors = colors,
        navigationIcon = navigationIcon,
        modifier = modifier.testTag("weiTopAppBar"),
    )
}

@Composable
fun BackNavigationIcon(onBackClick: () -> Unit) {
    IconButton(onClick = { onBackClick() }) {
        Icon(
            imageVector = WeiIcons.ArrowBack,
            contentDescription = "back",
        )
    }
}