package com.br.weightcontrol.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.br.weightcontrol.R
import com.br.weightcontrol.designsystem.icon.WeiIcons

@Composable
fun WeiFabButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = WeiIcons.Add,
            contentDescription = stringResource(R.string.content_description_fab),
        )
    }
}