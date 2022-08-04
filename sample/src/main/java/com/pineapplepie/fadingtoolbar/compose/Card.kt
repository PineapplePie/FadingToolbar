package com.pineapplepie.fadingtoolbar.compose

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
import androidx.compose.ui.unit.dp

@Composable
fun Card(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .fillMaxWidth()
            .height(160.dp)
            .background(color)
    )
}