package com.br.weightcontrol.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.weightcontrol.navigation.TopLevelDestination

@Composable
fun rememberNiaAppState(
    navController: NavHostController = rememberNavController(),
): WeiAppState {
    return remember(navController) {
        WeiAppState(navController)
    }
}

@Stable
class WeiAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
}
