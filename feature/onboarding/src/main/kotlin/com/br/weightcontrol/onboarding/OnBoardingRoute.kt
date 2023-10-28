@file:OptIn(ExperimentalMaterial3Api::class)

package com.br.weightcontrol.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.weightcontrol.designsystem.component.BackNavigationIcon
import com.br.weightcontrol.designsystem.component.WeiButton
import com.br.weightcontrol.designsystem.component.WeiTopAppBar
import com.br.weightcontrol.onboarding.domain.OnBoardingStep
import com.br.weightcontrol.onboarding.domain.OnBoardingStep.Companion.steps
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun OnBoardingRoute(
    navigateToProfile: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.onCreateProfile, uiState.onFinish) {
        when {
            uiState.onCreateProfile -> {
                navigateToProfile()
                viewModel.onCreateProfile()
            }
            uiState.onFinish -> {
                navigateToHome()
            }
        }
    }

    OnBoardingScreen(
        step = uiState.step,
        onNext = viewModel::onNext,
        onPreviews = viewModel::onPreviews,
        modifier = modifier
    )
}

@Composable
fun OnBoardingScreen(
    step: OnBoardingStep,
    onNext: (OnBoardingStep) -> Unit,
    onPreviews: (OnBoardingStep) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WeiTopAppBar(
            titleRes = R.string.welcome,
            navigationIcon = { BackNavigationIcon { onPreviews(step) } }
        )
        Text(
            text = stringResource(id = step.description),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )

        StepsProgress(step)

        WeiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            onClick = { onNext(step) }
        ) {
            Text(text = stringResource(id = step.buttonText))
        }
    }
}

@Composable
private fun StepsProgress(step: OnBoardingStep, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (position in steps.indices) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(getColorByStep(position, step))
                    .size(12.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
private fun getColorByStep(
    position: Int,
    step: OnBoardingStep
) = if (position == step.position) {
    MaterialTheme.colorScheme.primary
} else {
    MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
}
