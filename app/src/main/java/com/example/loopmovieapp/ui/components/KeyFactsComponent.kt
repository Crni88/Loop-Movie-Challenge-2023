package com.example.loopmovieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun KeyFactsComponent(
    modifier: Modifier = Modifier,
    isMoneyValue: Boolean = false,
    value: String = "Key Facts",
    title: Int,
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(66.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xFF141C25).copy(alpha = 0.05f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Column {
            MovieCaption(stringResource(title))
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                if (isMoneyValue) {
                    Body("$ ")
                }
                Body(value)
            }
        }
    }
}