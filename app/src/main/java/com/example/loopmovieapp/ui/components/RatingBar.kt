package com.example.loopmovieapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Double,
    maxRating: Int = 5,
    starSize: Dp = 24.dp,
    filledStarColor: Color = Color.Yellow,
    unfilledStarColor: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    val filledStars = rating.toInt()
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            if (i <= filledStars) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Filled star",
                    tint = filledStarColor,
                    modifier = Modifier.size(starSize)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Unfilled star",
                    tint = unfilledStarColor,
                    modifier = Modifier.size(starSize)
                )
            }
        }
    }
}
