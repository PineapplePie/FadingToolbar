package com.pineapplepie.fadingtoolbar_compose

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pineapplepie.fadingtoolbar_compose.config.TextConfig

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("ModifierParameter")
@Composable
fun FadingTopBarLazyColumn(
    contentModifier: Modifier = Modifier,
    listContent: LazyListScope.() -> Unit,
    topBarContent: @Composable RowScope.() -> Unit = {},
    topBarContentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    topBarModifier: Modifier = Modifier,
    topBarBackgroundColor: Color = MaterialTheme.colors.primarySurface,

    topBarText: String,
    topBarTextStyle: TextStyle = LocalTextStyle.current,
    topBarTextConfig: TextConfig = TextConfig.Builder().build(),

    footerText: String = topBarText,
    footerTextStyle: TextStyle = LocalTextStyle.current,
    footerTextConfig: TextConfig = TextConfig.Builder().build(),

    animationDuration: Int = DEFAULT_ANIMATION_DURATION,
    elevationSize: Dp = DEFAULT_ELEVATION_SIZE,
) {
    val shadowSize = remember { mutableStateOf(elevationSize) }
    val listState = rememberLazyListState()
    val showTitle = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = topBarModifier,
                backgroundColor = topBarBackgroundColor,
                contentPadding = topBarContentPadding,
                elevation = if (!showTitle.value && !listState.isScrollInProgress) 0.dp else shadowSize.value,
            ) {
                AnimatedVisibility(
                    visible = showTitle.value,
                    enter = fadeIn(animationSpec = tween(durationMillis = animationDuration)),
                    exit = fadeOut(animationSpec = tween(durationMillis = animationDuration)),
                ) {
                    AppBarText(topBarText, topBarTextConfig, topBarTextStyle)
                }
                topBarContent()
            }
        },
        content = { padding ->
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                LazyColumn(
                    modifier = contentModifier
                        .padding(padding)
                        .fillMaxWidth(),
                    state = listState,
                    content = {
                        item {
                            AppBarText(
                                text = footerText,
                                config = footerTextConfig,
                                style = footerTextStyle,
                            )
                        }
                        listContent()
                    })
            }
        }
    )
}

private const val DEFAULT_ANIMATION_DURATION = 300
private val DEFAULT_ELEVATION_SIZE = 4.dp
