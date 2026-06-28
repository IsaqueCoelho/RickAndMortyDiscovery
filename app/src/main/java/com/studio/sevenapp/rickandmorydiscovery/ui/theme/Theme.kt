package com.studio.sevenapp.rickandmorydiscovery.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.studio.sevenapp.domain.model.StatusEnum

val LocalRmColors = staticCompositionLocalOf { DarkRmColors }
val LocalRmDimens = staticCompositionLocalOf { RmDimens() }

/** Ergonomic accessor for the design tokens, mirroring how `MaterialTheme` is used. */
object RmTheme {
    val colors: RmColors
        @Composable @ReadOnlyComposable get() = LocalRmColors.current
    val dimens: RmDimens
        @Composable @ReadOnlyComposable get() = LocalRmDimens.current
}

/** Maps a character's status to its semantic token color in the current theme. */
@Composable
@ReadOnlyComposable
fun statusColor(status: StatusEnum): Color = when (status) {
    StatusEnum.ALIVE -> LocalRmColors.current.alive
    StatusEnum.DEAD -> LocalRmColors.current.dead
    StatusEnum.UNKNOWN -> LocalRmColors.current.unknown
}

/**
 * Holds the user-controlled light/dark preference so the top bar toggle can flip the whole app.
 */
@Stable
class ThemeController(isDark: Boolean) {
    var isDark by mutableStateOf(isDark)
        private set

    fun toggle() {
        isDark = !isDark
    }
}

val LocalThemeController = staticCompositionLocalOf { ThemeController(true) }

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val target = if (darkTheme) DarkRmColors else LightRmColors
    val spec = tween<Color>(durationMillis = RmMotion.DurationSlow, easing = RmMotion.Emphasized)

    val background by animateColorAsState(target.background, spec, label = "background")
    val surface by animateColorAsState(target.surface, spec, label = "surface")
    val surfaceVariant by animateColorAsState(target.surfaceVariant, spec, label = "surfaceVariant")
    val onBackground by animateColorAsState(target.onBackground, spec, label = "onBackground")
    val onSurface by animateColorAsState(target.onSurface, spec, label = "onSurface")
    val onSurfaceVariant by animateColorAsState(target.onSurfaceVariant, spec, label = "onSurfaceVariant")
    val outline by animateColorAsState(target.outline, spec, label = "outline")
    val primary by animateColorAsState(target.primary, spec, label = "primary")
    val primaryGlow by animateColorAsState(target.primaryGlow, spec, label = "primaryGlow")
    val accent by animateColorAsState(target.accent, spec, label = "accent")
    val onPrimary by animateColorAsState(target.onPrimary, spec, label = "onPrimary")
    val alive by animateColorAsState(target.alive, spec, label = "alive")
    val dead by animateColorAsState(target.dead, spec, label = "dead")
    val unknown by animateColorAsState(target.unknown, spec, label = "unknown")

    val rmColors = RmColors(
        background = background,
        surface = surface,
        surfaceVariant = surfaceVariant,
        onBackground = onBackground,
        onSurface = onSurface,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        primary = primary,
        primaryGlow = primaryGlow,
        accent = accent,
        onPrimary = onPrimary,
        alive = alive,
        dead = dead,
        unknown = unknown,
        isDark = darkTheme
    )

    val materialColors = if (darkTheme) {
        darkColors(
            primary = primary,
            primaryVariant = primaryGlow,
            secondary = accent,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onPrimary,
            onBackground = onBackground,
            onSurface = onSurface
        )
    } else {
        lightColors(
            primary = primary,
            primaryVariant = primaryGlow,
            secondary = accent,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onPrimary,
            onBackground = onBackground,
            onSurface = onSurface
        )
    }

    CompositionLocalProvider(
        LocalRmColors provides rmColors,
        LocalRmDimens provides RmDimens()
    ) {
        MaterialTheme(
            colors = materialColors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
