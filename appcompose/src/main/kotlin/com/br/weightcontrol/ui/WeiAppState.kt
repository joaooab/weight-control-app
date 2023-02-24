package com.br.weightcontrol.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.weightcontrol.history.navigation.historyNavigationRoute
import com.br.weightcontrol.home.navigation.homeNavigationRoute
import com.br.weightcontrol.navigation.TopLevelDestination
import com.br.weightcontrol.navigation.TopLevelDestination.*
import com.br.weightcontrol.settings.navigation.setupNavigationRoute

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

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> HOME
            historyNavigationRoute -> HISTORY
            setupNavigationRoute -> SETUP
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
}

