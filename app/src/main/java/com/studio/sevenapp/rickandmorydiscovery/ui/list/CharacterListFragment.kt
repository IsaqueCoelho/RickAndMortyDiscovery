package com.studio.sevenapp.rickandmorydiscovery.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.studio.sevenapp.rickandmorydiscovery.R
import com.studio.sevenapp.rickandmorydiscovery.ui.components.CharacterCard
import com.studio.sevenapp.rickandmorydiscovery.ui.components.StaggeredVerticalGrid
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

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    StaggeredVerticalGrid(
                        maxColumnWidth = 220.dp,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        characters.forEach { character ->
                            CharacterCard(
                                character = character,
                                onClick = {
                                    val bundle = bundleOf("characterId" to character.id)
                                    findNavController().navigate(R.id.characterDetail, bundle)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}