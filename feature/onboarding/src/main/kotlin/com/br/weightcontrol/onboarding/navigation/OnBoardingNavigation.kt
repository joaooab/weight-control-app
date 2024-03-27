package com.br.weightcontrol.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.weightcontrol.onboarding.OnBoardingRoute


const val onBoardingNavigationRoute = "onboarding_route"

fun NavGraphBuilder.onBoardingScreen(
    navigateToProfile: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable(route = onBoardingNavigationRoute) {
        OnBoardingRoute(
            navigateToProfile = navigateToProfile,
            navigateToHome = navigateToHome
        )
    }
}
