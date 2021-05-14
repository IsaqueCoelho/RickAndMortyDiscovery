package com.studio.sevenapp.rickandmorydiscovery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.studio.sevenapp.rickandmorydiscovery.R
import com.studio.sevenapp.rickandmorydiscovery.components.CharacterCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                val characters = viewModel.characterListMs.value

                LazyColumn {
                    itemsIndexed(
                        items = characters
                    ) { index, character ->
                        CharacterCard(
                            character = character,
                            onClick = {
                                findNavController().navigate(R.id.characterDetail)
                            }
                        )
                    }
                }

            }
        }
    }
}