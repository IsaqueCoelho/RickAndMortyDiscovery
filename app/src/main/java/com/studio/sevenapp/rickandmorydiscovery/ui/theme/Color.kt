package com.studio.sevenapp.rickandmorydiscovery.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Raw brand palette — the "Interdimensional Portal" identity. These are mode-independent
 * primitives; semantic meaning is assigned by [RmColors] below, which has a light and a dark set.
 */

// Portal / brand
val PortalGreen = Color(0xFF97CE4C)
val PortalGreenBright = Color(0xFFB6F36A)
val PortalGreenDeep = Color(0xFF3DA35D)
val PortalCyan = Color(0xFF24D0CB)
val PortalCyanDeep = Color(0xFF0E8F8A)
val Plumbus = Color(0xFFF2A0B5)

// Status
val AliveGreen = Color(0xFF6FE08A)
val AliveGreenDark = Color(0xFF2FA85B)
val DeadRed = Color(0xFFFF6B6B)
val DeadRedDark = Color(0xFFD64545)
val UnknownAmber = Color(0xFFE0B23C)
val UnknownAmberDark = Color(0xFFB8860B)

// Deep-space neutrals (dark mode)
val Space900 = Color(0xFF0A0F14)
val Space700 = Color(0xFF131C24)
val Space600 = Color(0xFF1B2730)
val Space500 = Color(0xFF26343C)
val MintWhite = Color(0xFFE6F0EA)
val MintMuted = Color(0xFF9DB2A8)

// Daylight neutrals (light mode)
val Light100 = Color(0xFFF4F7F4)
val Light0 = Color(0xFFFFFFFF)
val LightSurfaceVariant = Color(0xFFE7EFE8)
val LightOutline = Color(0xFFD6E0D8)
val InkGreen = Color(0xFF15211C)
val InkMuted = Color(0xFF5B6B63)

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
    background = Space900,
    surface = Space700,
    surfaceVariant = Space600,
    onBackground = MintWhite,
    onSurface = MintWhite,
    onSurfaceVariant = MintMuted,
    outline = Space500,
    primary = PortalGreen,
    primaryGlow = PortalGreenBright,
    accent = PortalCyan,
    onPrimary = Space900,
    alive = AliveGreen,
    dead = DeadRed,
    unknown = UnknownAmber,
    isDark = true
)

val LightRmColors = RmColors(
    background = Light100,
    surface = Light0,
    surfaceVariant = LightSurfaceVariant,
    onBackground = InkGreen,
    onSurface = InkGreen,
    onSurfaceVariant = InkMuted,
    outline = LightOutline,
    primary = PortalGreenDeep,
    primaryGlow = PortalGreen,
    accent = PortalCyanDeep,
    onPrimary = Light0,
    alive = AliveGreenDark,
    dead = DeadRedDark,
    unknown = UnknownAmberDark,
    isDark = false
)
