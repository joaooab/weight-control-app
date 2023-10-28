@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.br.weightcontrol.navigation.TopLevelDestination.*
import com.br.weightcontrol.settings.navigation.navigateToSetup
import com.br.weightcontrol.settings.navigation.setupNavigationRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberWeiAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    trackSheetState: SheetState = rememberModalBottomSheetState(),
): WeiAppState {
    return remember(navController) {
        WeiAppState(navController, coroutineScope, trackSheetState)
    }
}

@Stable
class WeiAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val trackSheetState: SheetState,
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

    val shouldShowFabButton: Boolean
        @Composable
        get() = HOME == currentTopLevelDestination

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            HOME -> navController.navigateToHome(topLevelNavOptions)
            HISTORY -> navController.navigateToHistory(topLevelNavOptions)
            SETUP -> navController.navigateToSetup(topLevelNavOptions)
        }
    }
}

