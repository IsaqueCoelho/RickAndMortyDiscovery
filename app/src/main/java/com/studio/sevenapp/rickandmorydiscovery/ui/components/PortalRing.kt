package com.studio.sevenapp.rickandmorydiscovery.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmTheme

/**
 * An endlessly rotating sweep-gradient ring — the portal that frames the character's avatar on the
 * detail screen. Two counter-rotating rings give the swirl real depth.
 */
@Composable
fun PortalRing(
    modifier: Modifier = Modifier,
    ringWidth: Dp = 10.dp
) {
    val glow = RmTheme.colors.primaryGlow
    val primary = RmTheme.colors.primary
    val accent = RmTheme.colors.accent

    val transition = rememberInfiniteTransition(label = "portal")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(RmMotion.DurationPortal, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )
    val counterRotation by transition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(RmMotion.DurationPortal * 2, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "counterRotation"
    )

    val outerSweep = Brush.sweepGradient(listOf(glow, accent, primary, glow))
    val innerSweep = Brush.sweepGradient(listOf(accent, glow, accent))

    Canvas(modifier = modifier) {
        val stroke = ringWidth.toPx()
        rotate(counterRotation) {
            drawCircle(
                brush = innerSweep,
                radius = size.minDimension / 2f - stroke * 2.4f,
                style = Stroke(width = stroke * 0.5f)
            )
        }
        rotate(rotation) {
            drawCircle(
                brush = outerSweep,
                radius = size.minDimension / 2f - stroke / 2f,
                style = Stroke(width = stroke)
            )
        }
    }
}
