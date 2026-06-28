package com.studio.sevenapp.rickandmorydiscovery.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing and radius tokens. Exposed through `RmTheme.dimens` so layouts share one rhythm.
 */
@Immutable
data class RmDimens(
    val spaceXs: Dp = 4.dp,
    val spaceSm: Dp = 8.dp,
    val spaceMd: Dp = 16.dp,
    val spaceLg: Dp = 24.dp,
    val spaceXl: Dp = 32.dp,
    val cardCorner: Dp = 20.dp,
    val cardElevation: Dp = 6.dp,
    val pillCorner: Dp = 100.dp
)
