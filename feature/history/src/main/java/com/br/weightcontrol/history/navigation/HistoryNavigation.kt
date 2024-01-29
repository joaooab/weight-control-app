package com.br.weightcontrol.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.br.weightcontrol.history.HistoryRoute

const val historyNavigationRoute = "history_route"

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(historyNavigationRoute, navOptions)
}

fun NavGraphBuilder.historyScreen() {
    composable(route = historyNavigationRoute) {
        HistoryRoute()
    }
}
