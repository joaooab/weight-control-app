package com.br.weightcontrol.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.br.weightcontrol.util.toPercent

@Composable
fun CustomLinearProgressIndicator(
    targetProgress: Float,
    modifier: Modifier = Modifier,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    var progress by remember { mutableStateOf(0f) }
    val progressAnimation: Float by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing), label = ""
    )
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(backgroundColor)
                .weight(1f)
                .height(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(progressColor)
                    .fillMaxHeight()
                    .fillMaxWidth(progressAnimation)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = (progressAnimation * 100).toPercent(),
            fontWeight = FontWeight.Bold
        )
    }
    LaunchedEffect(targetProgress) {
        progress = targetProgress
    }
}