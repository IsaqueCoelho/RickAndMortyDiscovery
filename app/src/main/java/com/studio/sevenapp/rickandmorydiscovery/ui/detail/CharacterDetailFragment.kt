package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getCharacterDetailsByID()

        return ComposeView(requireContext()).apply {
            setContent {

                val character = viewModel.characterMs.value

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Character Detail of ${character?.name}")
                }
            }
        }
    }

    private fun getCharacterDetailsByID() {
        val characterId = arguments?.getInt("characterId")
        viewModel.requestCharacterDetails(characterId)
    }
}