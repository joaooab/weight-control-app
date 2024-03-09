@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.br.weightcontrol.history.navigation.historyNavigationRoute
import com.br.weightcontrol.history.navigation.navigateToHistory
import com.br.weightcontrol.home.navigation.homeNavigationRoute
import com.br.weightcontrol.home.navigation.navigateToHome
import com.br.weightcontrol.navigation.TopLevelDestination
import com.br.weightcontrol.navigation.TopLevelDestination.HISTORY
import com.br.weightcontrol.navigation.TopLevelDestination.HOME
import com.br.weightcontrol.navigation.TopLevelDestination.SETUP
import com.br.weightcontrol.settings.navigation.navigateToSetup
import com.br.weightcontrol.settings.navigation.setupNavigationRoute

@Composable
fun rememberWeiAppState(
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

    val isTopLevelDestination: Boolean
        @Composable get() = currentTopLevelDestination != null

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            HOME -> navController.navigateToHome(topLevelNavOptions)
            HISTORY -> navController.navigateToHistory(topLevelNavOptions)
            SETUP -> navController.navigateToSetup(topLevelNavOptions)
        }
    }
}

