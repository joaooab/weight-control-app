/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.br.weightcontrol.navigation

import com.br.weightcontrol.R
import com.br.weightcontrol.designsystem.icon.Icon
import com.br.weightcontrol.designsystem.icon.WeiIcons


/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = Icon.ImageVectorIcon(WeiIcons.Home),
        unselectedIcon = Icon.ImageVectorIcon(WeiIcons.HomeBorder),
        iconTextId = R.string.home,
        titleTextId = R.string.app_name,
    ),
    HISTORY(
        selectedIcon = Icon.ImageVectorIcon(WeiIcons.History),
        unselectedIcon = Icon.ImageVectorIcon(WeiIcons.HistoryBorder),
        iconTextId = R.string.history,
        titleTextId = R.string.history,
    ),
    SETUP(
        selectedIcon = Icon.ImageVectorIcon(WeiIcons.Settings),
        unselectedIcon = Icon.ImageVectorIcon(WeiIcons.SettingsBorder),
        iconTextId = R.string.settings,
        titleTextId = R.string.settings,
    ),
}
