package com.pineapplepie.fadingtoolbar.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pineapplepie.fadingtoolbar.R
import com.pineapplepie.fadingtoolbar_compose.FadingTopBarColumn
import com.pineapplepie.fadingtoolbar_compose.config.TextConfig

class SampleComposeScrollViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScrollScreen()
        }
    }
}

@Composable
fun ScrollScreen() {
    val context = LocalContext.current
    val topBarTextConfig = TextConfig.Builder()
        .textFontSize(16.sp)
        .textColor(Color.Black)
        .textModifier(
            Modifier.padding(
                16.dp
            )
        )
        .build()
    val footerTextConfig = TextConfig.Builder()
        .textFontSize(32.sp)
        .textColor(Color.Black)
        .textModifier(
            Modifier.padding(
                16.dp
            )
        )
        .build()
    FadingTopBarColumn(
        content = {
            Card(Color.DarkGray)
            Card(Color.Cyan)
            Card(Color.Red)
            Card(Color.Yellow)
        },
        topBarText = context.getString(R.string.cards),
        topBarTextConfig = topBarTextConfig,
        topBarBackgroundColor = Color.White,
        footerText = context.getString(R.string.cards),
        footerTextConfig = footerTextConfig,
    )
}

@Composable
fun Card(color: Color) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .fillMaxWidth()
            .height(160.dp)
            .background(color)
    )
}