package com.br.weightcontrol.navigation

import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.SessionState
import com.br.weightcontrol.home.navigation.homeNavigationRoute
import com.br.weightcontrol.onboarding.navigation.onBoardingNavigationRoute

fun startDestination(sessionManager: SessionManager) = when (sessionManager.state.value) {
    is SessionState.OnBoarding -> onBoardingNavigationRoute
    else -> homeNavigationRoute
}