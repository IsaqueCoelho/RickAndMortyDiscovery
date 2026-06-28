package com.studio.sevenapp.rickandmorydiscovery.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject
constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    val characterListMs = mutableStateOf<List<PCharacter>>(listOf())

    /** True only while a *subsequent* page is being appended (not the initial load). */
    val isLoadingMore = mutableStateOf(false)

    /** Transient user-facing message (e.g. shown when the API rate-limits us). */
    val userMessage = mutableStateOf<String?>(null)

    private var nextPage: Int? = 1
    private var isFetching = false

    fun clearMessage() {
        userMessage.value = null
    }

    init {
        loadNextPage()
    }

    /**
     * Loads the next page and appends it. No-ops when a request is already in flight or when the
     * last page has already been reached ([nextPage] is null).
     */
    fun loadNextPage() {
        val pageToLoad = nextPage ?: return
        if (isFetching) return
        isFetching = true

        val isFirstPage = characterListMs.value.isEmpty()
        if (!isFirstPage) {
            isLoadingMore.value = true
        }

        viewModelScope.launch {
            try {
                val page = characterUseCase.getCharacters(pageToLoad)

                characterListMs.value = characterListMs.value + page.characters.map { character ->
                    PCharacter(
                        id = character.id,
                        name = character.name,
                        image = character.image,
                        status = character.status
                    )
                }
                nextPage = page.nextPage
            } catch (throwable: Throwable) {
                // Keep what we have; the next scroll-to-bottom will retry this page.
                userMessage.value = "Espere um pouco para carregar mais informações"
            } finally {
                isFetching = false
                isLoadingMore.value = false
            }
        }
    }
}
