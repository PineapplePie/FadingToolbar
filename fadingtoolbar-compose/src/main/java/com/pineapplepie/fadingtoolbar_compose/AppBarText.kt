package com.pineapplepie.fadingtoolbar_compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.pineapplepie.fadingtoolbar_compose.config.TextConfig

@Composable
internal fun AppBarText(
    text: String,
    config: TextConfig,
    style: TextStyle,
) {
    with(config) {
        Text(
            text = text,
            modifier = textModifier,
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