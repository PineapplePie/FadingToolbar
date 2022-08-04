package com.pineapplepie.fadingtoolbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pineapplepie.fadingtoolbar.compose.SampleComposeLazyColumnActivity
import com.pineapplepie.fadingtoolbar.databinding.ActivityLauncherBinding
import com.pineapplepie.fadingtoolbar.xml.SampleXmlRecyclerActivity
import com.pineapplepie.fadingtoolbar.xml.SampleXmlScrollViewActivity

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() = with(binding) {
        openRecyclerCard.setOnClickListener {
            startActivity(
                Intent(
                    this@LauncherActivity,
                    SampleXmlRecyclerActivity::class.java
                )
            )
        }

        openScrollViewCard.setOnClickListener {
            startActivity(
                Intent(
                    this@LauncherActivity,
                    SampleXmlScrollViewActivity::class.java
                )
            )
        }

        openRecyclerComposeCard.setOnClickListener {
            startActivity(
                Intent(
                    this@LauncherActivity,
                    SampleComposeLazyColumnActivity::class.java
                )
            )
        }
    }
}