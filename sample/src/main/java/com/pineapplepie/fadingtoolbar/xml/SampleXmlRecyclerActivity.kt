package com.pineapplepie.fadingtoolbar.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pineapplepie.fadingtoolbar.FadingToolbarAnimator
import com.pineapplepie.fadingtoolbar.databinding.ActivitySampleRecyclerBinding
import com.pineapplepie.fadingtoolbar.models
import com.pineapplepie.fadingtoolbar.xml.adapter.SampleAdapter

class SampleXmlRecyclerActivity : AppCompatActivity() {

   private val animator by lazy { FadingToolbarAnimator() }

   private lateinit var binding: ActivitySampleRecyclerBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivitySampleRecyclerBinding.inflate(layoutInflater)
      setContentView(binding.root)

      setupRecycler()
   }

   private fun setupRecycler() = with(binding) {
      val adapter = SampleAdapter()
      recyclerView.layoutManager = LinearLayoutManager(this@SampleXmlRecyclerActivity)
      recyclerView.adapter = adapter
      adapter.data = models

      animator.bind(toolbar, recyclerView)
   }
}