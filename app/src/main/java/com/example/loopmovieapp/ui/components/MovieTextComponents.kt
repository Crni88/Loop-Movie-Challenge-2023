package com.example.loopmovieapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MovieSubtitle(value: String) {
    Text(
        value,
        color = Color.Black.copy(alpha = 0.6f),
        fontSize = 14.sp,
        lineHeight = 16.71.sp,
        fontWeight = FontWeight(274)
    )
}

@Composable
fun MovieTitle(value: String) {
    Text(
        value,
        color = Color(0xFF141C25),
        fontSize = 24.sp,
        lineHeight = 28.64.sp,
        fontWeight = FontWeight(860)
    )
}

@Composable
fun MovieTitleSecondary(value: String) {
    Text(
        value,
        color = Color(0xFF141C25),
        fontSize = 24.sp,
        lineHeight = 28.64.sp,
        fontWeight = FontWeight(274)
    )
}

@Composable
fun MovieBodyTitle(value: String) {
    Text(
        value,
        color = Color(0xFF141C25),
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontWeight = FontWeight(700)
    )
}

@Composable
fun MovieBody(value: String) {
    Text(
        value,
        color = Color(0xFF141C25),
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(274)
    )
}

@Composable
fun MovieCaption(value: String) {
    Text(
        value,
        color = Color(0xFF141C25).copy(alpha = 0.7f),
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight(700)
    )
}


@Composable
fun Body(value: String) {
    Text(
        value,
        color = Color.Black,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(274)
    )
}
