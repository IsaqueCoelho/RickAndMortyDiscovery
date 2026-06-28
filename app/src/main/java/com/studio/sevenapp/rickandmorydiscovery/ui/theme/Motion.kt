package com.studio.sevenapp.rickandmorydiscovery.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

/**
 * Motion tokens — a single source of truth for durations and easings so every animation in the
 * app feels like it belongs to the same choreography.
 */
object RmMotion {
    const val DurationFast = 180
    const val DurationMedium = 350
    const val DurationSlow = 600
    const val DurationPortal = 6000

    /** Stagger between consecutive list/section items as they reveal. */
    const val StaggerStep = 45

    /** Material "emphasized" curve — quick out, gentle settle. */
    val Emphasized: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
    val EmphasizedDecelerate: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1f)
}
