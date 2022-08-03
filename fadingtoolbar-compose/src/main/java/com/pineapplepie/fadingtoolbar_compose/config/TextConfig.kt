package com.pineapplepie.fadingtoolbar_compose.config

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

class TextConfig private constructor(
    val textModifier: Modifier,
    val textColor: Color,
    val textFontSize: TextUnit,
    val textFontStyle: FontStyle?,
    val textFontWeight: FontWeight?,
    val textFontFamily: FontFamily?,
    val textLetterSpacing: TextUnit,
    val textDecoration: TextDecoration?,
    val textTextAlign: TextAlign?,
    val textLineHeight: TextUnit,
    val textOverflow: TextOverflow,
    val textSoftWrap: Boolean,
    val textMaxLines: Int,
    val textOnTextLayout: (TextLayoutResult) -> Unit,
) {
    data class Builder(
        private var textModifier: Modifier = Modifier,
        private var textColor: Color = Color.Unspecified,
        private var textFontSize: TextUnit = TextUnit.Unspecified,
        private var textFontStyle: FontStyle? = null,
        private var textFontWeight: FontWeight? = null,
        private var textFontFamily: FontFamily? = null,
        private var textLetterSpacing: TextUnit = TextUnit.Unspecified,
        private var textDecoration: TextDecoration? = null,
        private var textTextAlign: TextAlign? = null,
        private var textLineHeight: TextUnit = TextUnit.Unspecified,
        private var textOverflow: TextOverflow = TextOverflow.Clip,
        private var textSoftWrap: Boolean = true,
        private var textMaxLines: Int = Int.MAX_VALUE,
        private var textOnTextLayout: (TextLayoutResult) -> Unit = {}
    ) {
        fun textModifier(modifier: Modifier) = apply { this.textModifier = modifier }
        fun textColor(color: Color) = apply { this.textColor = color }
        fun textFontSize(textFontSize: TextUnit) = apply { this.textFontSize = textFontSize }
        fun textFontStyle(textFontStyle: FontStyle?) = apply { this.textFontStyle = textFontStyle }
        fun textFontWeight(textFontWeight: FontWeight?) = apply { this.textFontWeight = textFontWeight }
        fun textFontFamily(textFontFamily: FontFamily?) = apply { this.textFontFamily = textFontFamily }
        fun textLetterSpacing(textLetterSpacing: TextUnit) = apply { this.textLetterSpacing = textLetterSpacing }
        fun textDecoration(textDecoration: TextDecoration) = apply { this.textDecoration = textDecoration }
        fun textTextAlign(textTextAlign: TextAlign?) = apply { this.textTextAlign = textTextAlign }
        fun textLineHeight(textLineHeight: TextUnit) = apply { this.textLineHeight = textLineHeight }
        fun textOverflow(textOverflow: TextOverflow) = apply { this.textOverflow = textOverflow }
        fun textSoftWrap(textSoftWrap: Boolean) = apply { this.textSoftWrap = textSoftWrap }
        fun textMaxLines(textMaxLines: Int) = apply { this.textMaxLines = textMaxLines }
        fun textOnTextLayout(textOnTextLayout: (TextLayoutResult) -> Unit) =
            apply { this.textOnTextLayout = textOnTextLayout }

        fun build() = TextConfig(
            textModifier,
            textColor,
            textFontSize,
            textFontStyle,
            textFontWeight,
            textFontFamily,
            textLetterSpacing,
            textDecoration,
            textTextAlign,
            textLineHeight,
            textOverflow,
            textSoftWrap,
            textMaxLines,
            textOnTextLayout
        )
    }
}