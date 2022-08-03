package com.pineapplepie.fadingtoolbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pineapplepie.fadingtoolbar.compose.SampleComposeRecyclerActivity
import com.pineapplepie.fadingtoolbar.compose.SampleComposeScrollViewActivity
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
                    SampleComposeRecyclerActivity::class.java
                )
            )
        }

        openScrollViewComposeCard.setOnClickListener {
            startActivity(
                Intent(
                    this@LauncherActivity,
                    SampleComposeScrollViewActivity::class.java
                )
            )
        }
    }
}