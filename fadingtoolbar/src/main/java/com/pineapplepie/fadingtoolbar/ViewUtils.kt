package com.pineapplepie.fadingtoolbar

import android.animation.Animator
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal fun RecyclerView.addOnScrollListener(onScrolled: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            onScrolled()
        }
    })
}

/**
 * supports only a linear layout manager right now
 */
internal fun RecyclerView.isScrolledToTop(): Boolean {
    val elevationThreshold = context.getElevationThreshold()
    val layoutManager = (layoutManager as LinearLayoutManager)
    val position = layoutManager.findFirstVisibleItemPosition()
    val topView = layoutManager.findViewByPosition(position)
    return topView == null || (topView.top >= -elevationThreshold && position == 0)
}

/**
 * supports only a linear layout manager right now
 */
internal fun RecyclerView.isFirstItemVisible(): Boolean {
    return (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0
}

/**
 * used for finding a title in a Toolbar class
 */
internal fun ViewGroup.findTextView(): TextView? =
    children.firstOrNull { it is TextView } as? TextView


internal fun ViewPropertyAnimator.withCancelOrEndAction(cancelOrEnd: () -> Unit): ViewPropertyAnimator {
    setListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(p0: Animator) {
        }

        override fun onAnimationEnd(p0: Animator) {
            cancelOrEnd()
        }

        override fun onAnimationCancel(p0: Animator) {
            cancelOrEnd()
        }

        override fun onAnimationRepeat(p0: Animator) {
        }
    })
    return this
}