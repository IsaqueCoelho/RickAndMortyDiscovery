package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studio.sevenapp.rickandmorydiscovery.R
import com.studio.sevenapp.rickandmorydiscovery.ui.components.Surface
import com.studio.sevenapp.rickandmorydiscovery.ui.components.loadImage

@Composable
fun SnackImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        val imageBitmap =
            loadImage(
                url = imageUrl,
                defaultImage = R.drawable.ic_character_default
            ).value

        imageBitmap?.let { img ->
            Image(
                bitmap = img.asImageBitmap(),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}