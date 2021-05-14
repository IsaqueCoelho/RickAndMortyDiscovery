package com.studio.sevenapp.rickandmorydiscovery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.studio.sevenapp.rickandmorydiscovery.R
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacter


@Composable
fun CharacterCard(
    character: PCharacter,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                bottom = 4.dp,
                top = 8.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp,
    ) {
        Column {
            val imageBitmap =
                loadImage(
                    url = character.image,
                    defaultImage = R.drawable.ic_character_default
                ).value

            imageBitmap?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }

    }
}