package com.br.weightcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.br.weightcontrol.designsystem.theme.WeiTheme
import com.br.weightcontrol.domain.usecase.session.SessionState
import com.br.weightcontrol.ui.WeiApp
import com.google.android.gms.ads.MobileAds
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setupAds()
        enableEdgeToEdge()

        setContent {
            val sessionState by viewModel.sessionState.collectAsState()

            splashScreen.setKeepOnScreenCondition {
                sessionState == SessionState.Loading
            }

            if (sessionState !is SessionState.Loading) {
                WeiTheme {
                    WeiApp()
                }
            }
        }
    }

    private fun setupAds() {
        MobileAds.initialize(this) {}
    }
}
