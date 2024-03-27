package com.br.weightcontrol.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.br.weightcontrol.home.HomeRoute

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    navigateToTrack: () -> Unit,
    navigateToGoal: () -> Unit
) {
    composable(route = homeNavigationRoute) {
        HomeRoute(
            navigateToTrack = navigateToTrack,
            navigateToGoal = navigateToGoal
        )
    }
}
