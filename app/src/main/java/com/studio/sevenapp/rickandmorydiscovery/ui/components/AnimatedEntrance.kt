package com.studio.sevenapp.rickandmorydiscovery.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion
import kotlinx.coroutines.delay

/**
 * Reveals [content] with a staggered fade + rise + scale. [index] offsets the start so a grid of
 * items cascades into place instead of popping in all at once.
 */
@Composable
fun AnimatedEntrance(
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay((index * RmMotion.StaggerStep).toLong())
        visible = true
    }

    val spec = tween<Float>(durationMillis = RmMotion.DurationMedium, easing = RmMotion.EmphasizedDecelerate)
    val alpha by animateFloatAsState(if (visible) 1f else 0f, spec, label = "entranceAlpha")
    val translationY by animateFloatAsState(if (visible) 0f else 64f, spec, label = "entranceY")
    val scale by animateFloatAsState(if (visible) 1f else 0.92f, spec, label = "entranceScale")

    Box(
        modifier = modifier.graphicsLayer {
            this.alpha = alpha
            this.translationY = translationY
            this.scaleX = scale
            this.scaleY = scale
        }
    ) {
        content()
    }
}
