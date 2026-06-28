package com.studio.sevenapp.domain.model

/**
 * A single page of characters plus the cursor needed for infinite scroll.
 * [nextPage] is the number of the following page, or null when the last page has been reached.
 */
data class CharacterPage(
    val characters: List<Character>,
    val nextPage: Int?
)
