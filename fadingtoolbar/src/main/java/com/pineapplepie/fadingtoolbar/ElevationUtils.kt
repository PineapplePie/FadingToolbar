package com.pineapplepie.fadingtoolbar

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView

internal fun View.updateElevation(
    recyclerView: RecyclerView,
    size: Int,
) {
    val shouldHide = recyclerView.isScrolledToTop()
    elevation = if (shouldHide) 0f else size.toFloat()
}

internal fun View.updateElevation(
    scrollView: ScrollView,
    size: Int,
) {
    val shouldHide = scrollView.scrollY < context.getElevationThreshold()
    elevation = if (shouldHide) 0f else size.toFloat()
}

internal fun Context.getElevationThreshold() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    2f,
    resources.displayMetrics
)