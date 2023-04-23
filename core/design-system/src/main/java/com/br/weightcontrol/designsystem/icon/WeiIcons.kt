package com.br.weightcontrol.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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
    val Fitness = Icons.Filled.FitnessCenter
    val CalendarBorder = Icons.Outlined.CalendarMonth
    val ArrowBack = Icons.Filled.ArrowBack
    val Close = Icons.Filled.Close
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
