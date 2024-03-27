package com.br.weightcontrol.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val setupNavigationRoute = "setup_route"

fun NavController.navigateToSetup(navOptions: NavOptions? = null) {
    this.navigate(setupNavigationRoute, navOptions)
}

fun NavGraphBuilder.setupScreen(navigateToProfile: () -> Unit, navigateToGoal: () -> Unit) {
    composable(route = setupNavigationRoute) {
        SettingsRoute(navigateToProfile, navigateToGoal)
    }
}
