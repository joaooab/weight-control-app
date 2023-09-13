package com.br.weightcontrol.navigation

import br.com.weightcontrol.profile.navigation.profileNavigationRoute
import com.br.weightcontrol.domain.usecase.session.SessionManager
import com.br.weightcontrol.domain.usecase.session.SessionState
import com.br.weightcontrol.home.navigation.homeNavigationRoute

fun startDestination(sessionManager: SessionManager) = when (sessionManager.state.value) {
    is SessionState.OnBoarding -> profileNavigationRoute
    else -> homeNavigationRoute
}