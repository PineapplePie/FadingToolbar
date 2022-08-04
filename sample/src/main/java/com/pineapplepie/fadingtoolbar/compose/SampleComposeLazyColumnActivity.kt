package com.pineapplepie.fadingtoolbar.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pineapplepie.fadingtoolbar.R
import com.pineapplepie.fadingtoolbar_compose.FadingTopBarLazyColumn
import com.pineapplepie.fadingtoolbar_compose.config.TextConfig

class SampleComposeLazyColumnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyColumnScreen()
        }
    }
}

@Composable
fun LazyColumnScreen() {
    val context = LocalContext.current
    val topBarTextConfig = TextConfig.Builder()
        .textFontSize(20.sp)
        .textColor(Color.Black)
        .textFontWeight(FontWeight.SemiBold)
        .textModifier(
            Modifier.padding(
                16.dp, 8.dp, 16.dp, 8.dp
            )
        )
        .build()
    val footerTextConfig = TextConfig.Builder()
        .textFontSize(32.sp)
        .textColor(Color.Black)
        .textFontWeight(FontWeight.Bold)
        .textModifier(
            Modifier.padding(
                16.dp, 8.dp, 16.dp, 8.dp
            )
        )
        .build()
    FadingTopBarLazyColumn(
        listContent = {
            item { Card(Color.DarkGray) }
            item { Card(Color.Cyan) }
            item {
                Text(
                    context.getString(R.string.text_mock),
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            item { Card(Color.Magenta) }
            item {
                Text(
                    context.getString(R.string.text_mock_2),
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            item { Card(Color.Red) }
            item { Card(Color.Yellow) }
        },
        topBarText = context.getString(R.string.cards),
        topBarTextConfig = topBarTextConfig,
        topBarBackgroundColor = Color.White,
        footerTextConfig = footerTextConfig,
    )
}
