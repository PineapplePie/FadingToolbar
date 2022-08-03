package com.pineapplepie.fadingtoolbar

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class FadingToolbarAnimator {

    private lateinit var config: FadingToolbarConfig

    private var isAnimating = false

    fun setElevationSize(sizeInDp: Int) {
        if (!::config.isInitialized) {
            Log.e(this.javaClass.name, "You should call bind() method before any other configurations")
            return
        }
        config = config.copy(elevationSize = sizeInDp)
    }

    fun setAnimationDuration(duration: Long) {
        if (!::config.isInitialized) {
            Log.e(this.javaClass.name, "You should call bind() method before any other configurations")
            return
        }
        config = config.copy(animationDuration = duration)
    }

    fun bind(
        toolbar: MaterialToolbar,
        recyclerView: RecyclerView,
        withShadow: Boolean = true
    ) {
        val toolbarTextView = toolbar.findTextView() ?: return
        initialSetUp(toolbarTextView)

        recyclerView.addOnScrollListener {
            if (withShadow) toolbar.updateElevation(recyclerView, config.elevationSize)
            val isFirstItemVisible = recyclerView.isFirstItemVisible()
            animateToolbarText(toolbarTextView, !isFirstItemVisible)
        }
    }

    fun bind(
        toolbar: Toolbar,
        recyclerView: RecyclerView,
        withShadow: Boolean = true
    ) {
        val toolbarTextView = toolbar.findTextView() ?: return
        initialSetUp(toolbarTextView)

        recyclerView.addOnScrollListener {
            if (withShadow) toolbar.updateElevation(recyclerView, config.elevationSize)
            val isFirstItemVisible = recyclerView.isFirstItemVisible()
            animateToolbarText(toolbarTextView, !isFirstItemVisible)
        }
    }

    fun bind(
        toolbar: Toolbar,
        footerView: View,
        scrollView: ScrollView,
        withShadow: Boolean = true
    ) {
        val toolbarTextView = toolbar.findTextView() ?: return
        initialSetUp(toolbarTextView)

        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (withShadow) toolbar.updateElevation(scrollView, config.elevationSize)
            val scrollBounds = Rect()
            scrollView.getHitRect(scrollBounds)
            animateToolbarText(toolbarTextView, !footerView.getLocalVisibleRect(scrollBounds))
        }
    }

    fun bind(
        toolbar: MaterialToolbar,
        footerView: View,
        scrollView: ScrollView,
        withShadow: Boolean = true
    ) {
        val toolbarTextView = toolbar.findTextView() ?: return
        initialSetUp(toolbarTextView)

        scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            if (withShadow) toolbar.updateElevation(scrollView, config.elevationSize)
            val scrollBounds = Rect()
            scrollView.getHitRect(scrollBounds)
            animateToolbarText(toolbarTextView, !footerView.getLocalVisibleRect(scrollBounds))
        }
    }

    private fun initialSetUp(toolbarTextView: TextView) {
        toolbarTextView.alpha = DEFAULT_INVISIBLE_ALPHA

        config = FadingToolbarConfig(
            elevationSize =  toolbarTextView.context.resources.getDimensionPixelSize(R.dimen.default_shadow_size),
            animationDuration = DEFAULT_ANIMATION_DURATION
        )
    }

    private fun animateToolbarText(toolbarTextView: TextView, shouldShowTitle: Boolean) = with(toolbarTextView) {
        if (shouldShowTitle == isVisible) return@with
        if (isAnimating) return@with
        clearAnimation()

        val endAlpha: Float = if (shouldShowTitle) {
            DEFAULT_VISIBLE_ALPHA
        } else {
            DEFAULT_INVISIBLE_ALPHA
        }
        animate()
            .setDuration(config.animationDuration)
            .alpha(endAlpha)
            .withCancelOrEndAction {
                isVisible = shouldShowTitle
                isAnimating = false
            }
            .start()
    }
}

private const val DEFAULT_INVISIBLE_ALPHA = 0F
private const val DEFAULT_VISIBLE_ALPHA = 1F
private const val DEFAULT_ANIMATION_DURATION = 300L