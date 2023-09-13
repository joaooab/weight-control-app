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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.weightcontrol.profile.navigation.profileScreen
import com.br.weightcontrol.history.navigation.historyScreen
import com.br.weightcontrol.home.navigation.homeScreen
import com.br.weightcontrol.home.navigation.navigateToHome
import com.br.weightcontrol.settings.navigation.setupScreen
import com.br.weightcontrol.track.navigation.trackScreen
import org.koin.androidx.compose.get

@Composable
fun WeiNavHost(
    navController: NavHostController,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = startDestination(get()),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()
        historyScreen()
        setupScreen()
        profileScreen(
            onClose = navController::navigateToHome,
            onShowSnackBar = onShowSnackBar
        )
        trackScreen(onBackClick = navController::popBackStack)
    }
}
