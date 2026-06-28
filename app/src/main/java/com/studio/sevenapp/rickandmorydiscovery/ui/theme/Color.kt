package com.studio.sevenapp.rickandmorydiscovery.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Brand palette derived from the supplied five-swatch palette:
 * cocoa · peach · lemon · lime · orchid. These are mode-independent primitives; semantic meaning is
 * assigned by [RmColors] below, which has a light and a dark set.
 */

// The five source swatches
val Cocoa = Color(0xFF3E2A1E)   // deep brown
val Peach = Color(0xFFE2AA8B)   // warm tan
val Lemon = Color(0xFFEEE150)   // yellow
val Lime = Color(0xFF93CE55)    // green (portal)
val Orchid = Color(0xFFE4A1CE)  // pink (plumbus)

// Derived lime / orchid shades for fills, glows and on-light contrast
val LimeBright = Color(0xFFB6E86F)
val LimeDeep = Color(0xFF5FA336)
val OrchidDeep = Color(0xFFC56FA8)
val PeachDeep = Color(0xFFC9784E)

// Cocoa-based espresso neutrals (dark mode)
val CocoaDeep = Color(0xFF241710)
val CocoaSurface = Color(0xFF33241A)
val CocoaSurfaceVariant = Color(0xFF45331F)
val CocoaOutline = Color(0xFF4F3B2A)
val Cream = Color(0xFFF4E9DF)
val CreamMuted = Color(0xFFC4AD99)

// Warm daylight neutrals (light mode)
val Linen = Color(0xFFFBF4EC)
val White = Color(0xFFFFFFFF)
val LinenSurfaceVariant = Color(0xFFF3E7DA)
val LinenOutline = Color(0xFFE7D6C6)
val InkMuted = Color(0xFF7A6555)

// Status — alive (lime) and unknown (lemon) come straight from the palette; dead is a warm
// terracotta red kept in the cocoa/peach family so it harmonises with the rest.
val DeadCoral = Color(0xFFE0705A)
val DeadCoralDark = Color(0xFFC2452F)
val UnknownLemonDark = Color(0xFFB58A12)
val AliveLimeDark = Color(0xFF4FA838)

/**
 * Semantic color tokens. Components read these via `RmTheme.colors.*` so the same code renders
 * correctly in both light and dark mode.
 */
@Immutable
data class RmColors(
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val primary: Color,
    val primaryGlow: Color,
    val accent: Color,
    val onPrimary: Color,
    val alive: Color,
    val dead: Color,
    val unknown: Color,
    val isDark: Boolean
)

val DarkRmColors = RmColors(
    background = CocoaDeep,
    surface = CocoaSurface,
    surfaceVariant = CocoaSurfaceVariant,
    onBackground = Cream,
    onSurface = Cream,
    onSurfaceVariant = CreamMuted,
    outline = CocoaOutline,
    primary = Lime,
    primaryGlow = LimeBright,
    accent = Orchid,
    onPrimary = CocoaDeep,
    alive = Lime,
    dead = DeadCoral,
    unknown = Lemon,
    isDark = true
)

val LightRmColors = RmColors(
    background = Linen,
    surface = White,
    surfaceVariant = LinenSurfaceVariant,
    onBackground = Cocoa,
    onSurface = Cocoa,
    onSurfaceVariant = InkMuted,
    outline = LinenOutline,
    primary = LimeDeep,
    primaryGlow = Lime,
    accent = OrchidDeep,
    onPrimary = White,
    alive = AliveLimeDark,
    dead = DeadCoralDark,
    unknown = UnknownLemonDark,
    isDark = false
)
