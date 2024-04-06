package com.br.weightcontrol.track.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.br.weightcontrol.track.TrackRoute

const val trackNavigationRoute = "track_route"

fun NavController.navigateToTrack() {
    this.navigate(trackNavigationRoute)
}

fun NavGraphBuilder.trackScreen(
    onClose: () -> Unit,
    onShowSnackBar: suspend (String, String?) -> Boolean,
) {
    composable(route = trackNavigationRoute) {
        TrackRoute(
            onClose = onClose,
            onShowSnackBar = onShowSnackBar
        )
    }
}
