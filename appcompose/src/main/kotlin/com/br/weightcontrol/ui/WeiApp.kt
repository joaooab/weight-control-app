package com.br.weightcontrol.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.br.weightcontrol.designsystem.component.WeiBackground
import com.br.weightcontrol.designsystem.component.WeiGradientBackground
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.designsystem.theme.LocalGradientColors
import com.br.weightcontrol.navigation.WeiNavHost
import com.br.weightcontrol.track.navigation.navigateToTrack

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun WeiApp(appState: WeiAppState = rememberWeiAppState()) {
    WeiBackground {
        WeiGradientBackground(gradientColors = LocalGradientColors.current) {
            Scaffold(
                modifier = Modifier.semantics { testTagsAsResourceId = true },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                floatingActionButton = {
                    WeiFabButton(
                        onClick = {
                            appState.navController.navigateToTrack()
                        }
                    )
                },
                bottomBar = {
                    WeiBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.testTag("WeiBottomBar"),
                    )
                }
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    Column(Modifier.fillMaxSize()) {
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            WeiTopAppBar(
                                titleRes = destination.titleTextId,
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color.Transparent,
                                ),
                            )
                        }

                        WeiNavHost(appState.navController)
                    }
                }
            }
        }
    }
}
