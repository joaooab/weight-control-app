package br.com.weightcontrol.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.weightcontrol.profile.ProfileRoute

const val profileNavigationRoute = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(profileNavigationRoute, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
) {
    composable(route = profileNavigationRoute) {
        ProfileRoute(
            onClose = onClose,
            onShowSnackBar = onShowSnackBar
        )
    }
}
