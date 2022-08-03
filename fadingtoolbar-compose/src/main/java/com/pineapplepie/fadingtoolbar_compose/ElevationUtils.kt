package com.pineapplepie.fadingtoolbar_compose

import android.content.Context
import android.util.TypedValue

fun Context.getElevationThreshold() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    2f,
    resources.displayMetrics
)
