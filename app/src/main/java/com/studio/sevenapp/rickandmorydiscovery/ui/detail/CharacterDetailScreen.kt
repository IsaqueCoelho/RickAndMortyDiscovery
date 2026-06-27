package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.ScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.studio.sevenapp.domain.model.GenderEnum
import com.studio.sevenapp.domain.model.StatusEnum
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacterInDetail
import com.studio.sevenapp.rickandmorydiscovery.model.PEpisode
import com.studio.sevenapp.rickandmorydiscovery.model.PLocation
import com.studio.sevenapp.rickandmorydiscovery.ui.components.Divider
import com.studio.sevenapp.rickandmorydiscovery.ui.components.Surface
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.Typography
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.Yellow200
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.Yellow500
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.Yellow700
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBackClick: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(characterId) {
        viewModel.requestCharacterDetails(characterId)
    }

    val character = viewModel.characterMs.value

    character?.let {
        Box(Modifier.fillMaxSize()) {
            val scroll = rememberScrollState(0)
            Header()
            Body(character, scroll)
            Title(
                name = character.name,
                gender = character.gender,
                specie = character.species,
                status = character.status,
                scroll = scroll.value
            )
            CharacterImage(character.image, scroll.value)
            Up(upPress = onBackClick)
        }
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Yellow700)
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Yellow700.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            tint = Yellow200,
            contentDescription = "Back"
        )
    }
}

@Composable
private fun Title(
    name: String,
    gender: GenderEnum,
    specie: String,
    status: StatusEnum,
    scroll: Int
) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)

    val genderAndSpecie: String = when {
        specie.isNotEmpty() -> gender.value.capitalizeFirst() + " | $specie"
        else -> gender.value.capitalizeFirst()
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .graphicsLayer { translationY = offset }
            .background(color = Yellow500)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = name,
            style = Typography.h4,
            modifier = HzPadding
        )
        Text(
            text = genderAndSpecie,
            style = Typography.subtitle2,
            fontSize = 20.sp,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = status.value.capitalizeFirst(),
            style = Typography.h6,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
        Divider()
    }
}

@Composable
private fun CharacterImage(
    imageUrl: String,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding
    ) {
        SnackImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth =
            lerp(imageMaxSize.toDp(), imageMinSize.toDp(), collapseFraction).roundToPx()
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            ((constraints.maxWidth - imageWidth) / 2).toDp(), // centered when expanded
            (constraints.maxWidth - imageWidth).toDp(), // right aligned when collapsed
            collapseFraction
        ).roundToPx()
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.place(imageX, imageY)
        }
    }
}

@Composable
private fun Body(
    character: PCharacterInDetail,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MinTitleOffset)
                .padding(horizontal = 24.dp)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    ShowLoaction(origin = character.origin, location = character.location)
                    ShowEpisodes(episodes = character.episodes)
                }
            }
        }
    }
}

@Composable
private fun ShowLoaction(origin: PLocation, location: PLocation) {
    Column(
        modifier = HzPadding
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Showed in:",
            style = MaterialTheme.typography.h5
        )
        Spacer(Modifier.height(4.dp))
        Text(
            style = MaterialTheme.typography.body1,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Origin: ")
                }
                append(origin.name)
            })

        Spacer(Modifier.height(4.dp))
        Text(
            style = MaterialTheme.typography.body1,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Last known location: ")
                }
                append(location.name)
            },
        )
    }
}

@Composable
private fun ShowEpisodes(
    episodes: List<PEpisode>
) {
    Column(
        modifier = HzPadding
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Episodes:",
            style = MaterialTheme.typography.h5
        )

        episodes.forEach { episode ->
            Spacer(Modifier.height(16.dp))
            Text(
                style = MaterialTheme.typography.body1,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Code: ")
                    }
                    append(episode.episodeCode)
                },
            )
            Text(
                style = MaterialTheme.typography.body1,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Name: ")
                    }
                    append(episode.name)
                },
            )
            Text(
                style = MaterialTheme.typography.body1,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Air Date: ")
                    }
                    append(episode.airDate)
                },
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

private fun String.capitalizeFirst(locale: Locale = Locale.getDefault()): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
