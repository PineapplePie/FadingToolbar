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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
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

    topBarNavigationContent: @Composable RowScope.() -> Unit = {},
    topBarActionContent: @Composable RowScope.() -> Unit = {},
    topBarContentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    topBarModifier: Modifier = Modifier,
    topBarBackgroundColor: Color = MaterialTheme.colors.primarySurface,

    topBarText: String,
    topBarTextStyle: TextStyle = LocalTextStyle.current,
    topBarTextConfig: TextConfig = TextConfig.Builder().build(),

    headerText: String = topBarText,
    headerTextStyle: TextStyle = LocalTextStyle.current,
    headerTextConfig: TextConfig = TextConfig.Builder().build(),

    animationDuration: Int = DEFAULT_ANIMATION_DURATION,
    elevationSize: Dp = DEFAULT_ELEVATION_SIZE,
) {
    val listState = rememberLazyListState()
    val showTitle = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }

    val showElevation = remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset > 0
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = topBarModifier,
                backgroundColor = topBarBackgroundColor,
                contentPadding = topBarContentPadding,
                elevation = if (showElevation.value) elevationSize else 0.dp,
            ) {
                topBarNavigationContent()
                AnimatedVisibility(
                    visible = showTitle.value,
                    enter = fadeIn(animationSpec = tween(durationMillis = animationDuration)),
                    exit = fadeOut(animationSpec = tween(durationMillis = animationDuration)),
                ) {
                    AppBarText(topBarText, topBarTextConfig, topBarTextStyle)
                }
                topBarActionContent()
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
                                text = headerText,
                                config = headerTextConfig,
                                style = headerTextStyle,
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
