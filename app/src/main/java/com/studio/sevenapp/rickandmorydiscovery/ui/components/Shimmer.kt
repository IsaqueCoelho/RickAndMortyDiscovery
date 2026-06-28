package com.studio.sevenapp.rickandmorydiscovery.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmTheme

/** A horizontally sweeping gradient used as a placeholder fill while content loads. */
@Composable
fun rememberShimmerBrush(): Brush {
    val base = RmTheme.colors.surfaceVariant
    val highlight = RmTheme.colors.outline

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translate by transition.animateFloat(
        initialValue = -500f,
        targetValue = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1300, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerX"
    )

    return Brush.linearGradient(
        colors = listOf(base, highlight, base),
        start = Offset(translate, 0f),
        end = Offset(translate + 400f, 400f)
    )
}

/** Placeholder grid shown before the first characters arrive. */
@Composable
fun LoadingGrid(modifier: Modifier = Modifier) {
    val brush = rememberShimmerBrush()
    StaggeredVerticalGrid(
        maxColumnWidth = 220.dp,
        modifier = modifier.padding(8.dp)
    ) {
        repeat(8) {
            ShimmerCard(brush)
        }
    }
}

@Composable
private fun ShimmerCard(brush: Brush) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(RmTheme.dimens.cardCorner))
            .background(RmTheme.colors.surface)
            .padding(8.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(brush)
        )
        Spacer(Modifier.height(12.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(16.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(brush)
        )
        Spacer(Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(14.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(brush)
        )
        Spacer(Modifier.height(8.dp))
    }
}
