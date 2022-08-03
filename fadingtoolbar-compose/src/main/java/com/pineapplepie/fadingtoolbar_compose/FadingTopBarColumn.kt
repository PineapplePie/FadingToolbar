package com.pineapplepie.fadingtoolbar_compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pineapplepie.fadingtoolbar_compose.config.TextConfig

@Composable
fun FadingTopBarColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    topBarContent: @Composable RowScope.() -> Unit = {},
    topBarModifier: Modifier = Modifier,
    topBarBackgroundColor: Color = MaterialTheme.colors.primarySurface,

    footerText: String,
    footerTextStyle: TextStyle = LocalTextStyle.current,
    footerTextConfig: TextConfig = TextConfig.Builder().build(),

    topBarText: String,
    topBarTextStyle: TextStyle = LocalTextStyle.current,
    topBarTextConfig: TextConfig = TextConfig.Builder().build(),

    animationDuration: Int = DEFAULT_ANIMATION_DURATION,
    elevationSize: Dp = DEFAULT_ELEVATION_SIZE,
    topBarContentPadding: PaddingValues = AppBarDefaults.ContentPadding,
) {
    val context = LocalContext.current
    val shadowSize = remember { mutableStateOf(elevationSize) }
    val scrollState = rememberScrollState()

    val footerOffset = remember { mutableStateOf(Offset.Zero) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = topBarModifier,
                backgroundColor = topBarBackgroundColor,
                contentPadding = topBarContentPadding,
                elevation = if (scrollState.value < context.getElevationThreshold()) 0.dp else shadowSize.value,
            ) {
                AnimatedVisibility(
                    visible = footerOffset.value.y < 0,
                    enter = fadeIn(animationSpec = tween(durationMillis = animationDuration)),
                    exit = fadeOut(animationSpec = tween(durationMillis = animationDuration)),
                ) {
                    TextWithConfig(topBarText, topBarTextConfig, topBarTextStyle)
                }
                topBarContent()
            }
        },
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .verticalScroll(scrollState)
                    .fillMaxWidth(),
                content = {
                    val footerOffsetCallback: (Offset) -> Unit = { off -> footerOffset.value = off }
                    TextWithConfig(
                        footerText,
                        footerTextConfig,
                        footerTextStyle,
                        footerOffsetCallback,
                    )
                    content()
                }
            )
        }
    )
}

@Composable
internal fun TextWithConfig(
    text: String,
    config: TextConfig,
    style: TextStyle,
    onOffsetChanged: (Offset) -> Unit = {}
) {
    with(config) {
        Text(
            text = text,
            modifier = textModifier.onGloballyPositioned { onOffsetChanged(it.positionInRoot()) },
            color = textColor,
            fontSize = textFontSize,
            fontStyle = textFontStyle,
            fontWeight = textFontWeight,
            fontFamily = textFontFamily,
            letterSpacing = textLetterSpacing,
            textDecoration = textDecoration,
            textAlign = textTextAlign,
            lineHeight = textLineHeight,
            overflow = textOverflow,
            softWrap = textSoftWrap,
            maxLines = textMaxLines,
            onTextLayout = textOnTextLayout,
            style = style,
        )
    }
}

private const val DEFAULT_ANIMATION_DURATION = 300
private val DEFAULT_ELEVATION_SIZE = 4.dp
