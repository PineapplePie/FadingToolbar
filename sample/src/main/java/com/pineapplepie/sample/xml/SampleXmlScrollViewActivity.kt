package com.pineapplepie.sample.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pineapplepie.fadingtoolbar.FadingToolbarAnimator
import com.pineapplepie.sample.databinding.ActivitySampleScrollViewBinding

class SampleXmlScrollViewActivity : AppCompatActivity() {

    private val animator by lazy { FadingToolbarAnimator() }

    private lateinit var binding: ActivitySampleScrollViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleScrollViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupScrollView()
    }

    private fun setupScrollView() = with(binding) {
        animator.bind(toolbar, headerText, scrollView)
    }
}