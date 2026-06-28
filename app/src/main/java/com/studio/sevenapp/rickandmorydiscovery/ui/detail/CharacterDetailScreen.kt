package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.studio.sevenapp.rickandmorydiscovery.ui.components.AnimatedEntrance
import com.studio.sevenapp.rickandmorydiscovery.ui.components.Divider
import com.studio.sevenapp.rickandmorydiscovery.ui.components.PortalRing
import com.studio.sevenapp.rickandmorydiscovery.ui.components.StatusPill
import com.studio.sevenapp.rickandmorydiscovery.ui.components.Surface
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmTheme
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.Typography
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RmTheme.colors.background)
    ) {
        character?.let {
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
        }
        Up(upPress = onBackClick)
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        RmTheme.colors.primary.copy(alpha = 0.55f),
                        RmTheme.colors.background
                    )
                )
            )
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.85f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "backScale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .size(44.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(CircleShape)
            .background(RmTheme.colors.surface.copy(alpha = 0.85f))
            .border(1.dp, RmTheme.colors.outline, CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = upPress
            )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            tint = RmTheme.colors.primary,
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
        specie.isNotEmpty() -> gender.value.capitalizeFirst() + "  |  $specie"
        else -> gender.value.capitalizeFirst()
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .graphicsLayer { translationY = offset }
            .fillMaxWidth()
            .background(RmTheme.colors.background)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = name,
            style = Typography.h4,
            color = RmTheme.colors.onBackground,
            modifier = HzPadding
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = genderAndSpecie,
            style = Typography.subtitle2,
            color = RmTheme.colors.onSurfaceVariant,
            modifier = HzPadding
        )
        Spacer(Modifier.height(14.dp))
        Box(modifier = HzPadding) {
            StatusPill(status = status)
        }
        Spacer(Modifier.height(18.dp))
        Divider(color = RmTheme.colors.outline)
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
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            PortalRing(modifier = Modifier.fillMaxSize())
            SnackImage(
                imageUrl = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.76f)
            )
        }
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
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            ) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    AnimatedEntrance(index = 0) {
                        LocationSection(origin = character.origin, location = character.location)
                    }
                    EpisodesSection(episodes = character.episodes)
                    Spacer(Modifier.height(32.dp))
                    // Keep the last episode clear of the gesture / navigation bar.
                    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                }
            }
        }
    }
}

@Composable
private fun LocationSection(origin: PLocation, location: PLocation) {
    Column(modifier = HzPadding) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Showed in",
            style = Typography.h5,
            color = RmTheme.colors.primary
        )
        Spacer(Modifier.height(12.dp))
        InfoRow(label = "Origin", value = origin.name)
        Spacer(Modifier.height(8.dp))
        InfoRow(label = "Last known location", value = location.name)
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    val labelColor = RmTheme.colors.onBackground
    Text(
        style = Typography.body1,
        color = RmTheme.colors.onSurface,
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = labelColor)) {
                append("$label: ")
            }
            append(value)
        }
    )
}

@Composable
private fun EpisodesSection(episodes: List<PEpisode>) {
    Column(modifier = HzPadding) {
        Spacer(Modifier.height(28.dp))
        Text(
            text = "Episodes",
            style = Typography.h5,
            color = RmTheme.colors.accent
        )
        Spacer(Modifier.height(12.dp))
        episodes.forEachIndexed { index, episode ->
            AnimatedEntrance(index = index + 1) {
                EpisodeCard(episode)
            }
        }
    }
}

@Composable
private fun EpisodeCard(episode: PEpisode) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RmTheme.colors.surfaceVariant)
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(RmTheme.colors.accent.copy(alpha = 0.18f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = episode.episodeCode,
                    color = RmTheme.colors.accent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                text = episode.name,
                color = RmTheme.colors.onSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = episode.airDate,
            color = RmTheme.colors.onSurfaceVariant,
            fontSize = 12.sp
        )
    }
}

private fun String.capitalizeFirst(locale: Locale = Locale.getDefault()): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
