package com.studio.sevenapp.rickandmorydiscovery.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmTheme

/**
 * A sun ⇆ moon toggle. The icon rotates as it morphs: in dark mode it shows a sun (tap for light),
 * in light mode a crescent moon (tap for dark). The crescent is carved with a [BlendMode.Clear]
 * punch-out so it animates cleanly.
 */
@Composable
fun ThemeToggleButton(
    isDark: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconColor = RmTheme.colors.primary
    val rayProgress by animateFloatAsState(
        targetValue = if (isDark) 1f else 0f,
        animationSpec = tween(RmMotion.DurationMedium, easing = RmMotion.Emphasized),
        label = "rayProgress"
    )
    val rotation by animateFloatAsState(
        targetValue = if (isDark) 0f else 360f,
        animationSpec = tween(RmMotion.DurationSlow, easing = RmMotion.Emphasized),
        label = "iconRotation"
    )

    androidx.compose.foundation.Canvas(
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(RmTheme.colors.surfaceVariant)
            .border(1.dp, RmTheme.colors.outline, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onToggle
            )
            .rotate(rotation)
            .semantics { contentDescription = if (isDark) "Switch to light mode" else "Switch to dark mode" }
    ) {
        val c = center
        val bodyRadius = size.minDimension * 0.2f

        // Sun rays — fade/grow in dark mode.
        if (rayProgress > 0.01f) {
            val rayLength = bodyRadius * 0.9f * rayProgress
            val rayStart = bodyRadius * 1.5f
            for (i in 0 until 8) {
                val angle = Math.toRadians((i * 45).toDouble())
                val dx = kotlin.math.cos(angle).toFloat()
                val dy = kotlin.math.sin(angle).toFloat()
                drawLine(
                    color = iconColor.copy(alpha = rayProgress),
                    start = Offset(c.x + dx * rayStart, c.y + dy * rayStart),
                    end = Offset(c.x + dx * (rayStart + rayLength), c.y + dy * (rayStart + rayLength)),
                    strokeWidth = size.minDimension * 0.04f
                )
            }
        }

        // Disc, with a moon crescent carved out as it leaves dark mode.
        drawIntoCanvas { canvas: Canvas ->
            val paint = Paint().apply { color = iconColor }
            canvas.saveLayer(
                bounds = androidx.compose.ui.geometry.Rect(Offset.Zero, size),
                paint = Paint()
            )
            canvas.drawCircle(c, bodyRadius, paint)
            val crescent = (1f - rayProgress)
            if (crescent > 0.01f) {
                val cut = Paint().apply { blendMode = BlendMode.Clear }
                canvas.drawCircle(
                    Offset(c.x + bodyRadius * 0.7f, c.y - bodyRadius * 0.5f),
                    bodyRadius * (0.85f + 0.15f * crescent),
                    cut
                )
            }
            canvas.restore()
        }
    }
}
