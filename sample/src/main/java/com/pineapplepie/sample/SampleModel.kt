package com.pineapplepie.sample

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

sealed class UiModel

data class TitleModel(@StringRes val text: Int) : UiModel()

data class SampleModel(@ColorRes val colorRes: Int): UiModel()

val models = mutableListOf(
    TitleModel(R.string.cards),
    SampleModel(R.color.purple_200),
    SampleModel(R.color.purple_700),
    SampleModel(R.color.purple_500),
    SampleModel(R.color.teal_200),
    SampleModel(R.color.teal_700),
    SampleModel(R.color.black)
)