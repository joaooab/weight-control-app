package br.com.weightcontrol.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.weightcontrol.GoalRoute

const val goalNavigationRoute = "goal_route"

fun NavController.navigateToGoal() {
    this.navigate(goalNavigationRoute)
}

fun NavGraphBuilder.goalScreen(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
) {
    composable(route = goalNavigationRoute) {
        GoalRoute(
            onClose = onClose,
            onShowSnackBar = onShowSnackBar
        )
    }
}
