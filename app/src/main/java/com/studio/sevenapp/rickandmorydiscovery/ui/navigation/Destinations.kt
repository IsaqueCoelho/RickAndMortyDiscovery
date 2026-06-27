package com.studio.sevenapp.rickandmorydiscovery.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation destinations. Each route is a [Serializable] type consumed by
 * Navigation Compose's `composable<T>` / `toRoute<T>()` APIs, so arguments are passed and
 * read as strongly-typed values instead of string keys and bundles.
 */
@Serializable
object CharacterListRoute

@Serializable
data class CharacterDetailRoute(val characterId: Int)
