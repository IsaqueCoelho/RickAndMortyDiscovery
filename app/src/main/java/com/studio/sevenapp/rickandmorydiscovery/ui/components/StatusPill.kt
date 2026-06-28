package com.studio.sevenapp.rickandmorydiscovery.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studio.sevenapp.domain.model.StatusEnum
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.statusColor

/**
 * Status chip whose dot softly pulses with a radiating glow — alive/dead/unknown each carry their
 * own semantic token color.
 */
@Composable
fun StatusPill(
    status: StatusEnum,
    modifier: Modifier = Modifier
) {
    val color = statusColor(status)

    val transition = rememberInfiniteTransition(label = "statusPulse")
    val pulse by transition.animateFloat(
        initialValue = 0.45f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(RmMotion.DurationSlow + 300),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )
    val glow by transition.animateFloat(
        initialValue = 3f,
        targetValue = 7f,
        animationSpec = infiniteRepeatable(
            animation = tween(RmMotion.DurationSlow + 300),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseGlow"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(CircleShape)
            .background(color.copy(alpha = 0.14f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(8.dp)
                .drawBehind {
                    drawCircle(color = color.copy(alpha = 0.35f), radius = glow.dp.toPx())
                    drawCircle(color = color.copy(alpha = pulse), radius = 4.dp.toPx())
                }
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = status.value,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
