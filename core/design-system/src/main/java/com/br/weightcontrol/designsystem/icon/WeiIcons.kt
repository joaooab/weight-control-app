package com.br.weightcontrol.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object WeiIcons {
    val Home = Icons.Filled.Home
    val HomeBorder = Icons.Outlined.Home
    val History = Icons.Filled.History
    val HistoryBorder = Icons.Outlined.History
    val Settings = Icons.Filled.Settings
    val SettingsBorder = Icons.Outlined.Settings
    val Speed = Icons.Filled.Speed
    val Add = Icons.Filled.Add
    val CalendarBorder = Icons.Outlined.CalendarMonth
    val ArrowBack = Icons.Filled.ArrowBack
    val Close = Icons.Filled.Close
    val Person = Icons.Outlined.Person
    val Edit = Icons.Outlined.Edit
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
