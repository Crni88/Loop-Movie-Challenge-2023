package com.example.loopmovieapp.com.example.loopmovieapp.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun MoviePoster(moviePosterUrl: String) {
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(moviePosterUrl) {
        Glide.with(context)
            .asBitmap()
            .load(moviePosterUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }

                override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {
                    bitmap = null
                }
            })
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Movie Image",
            modifier = Modifier
                .shadow(30.dp, shape = RoundedCornerShape(14.dp))
                .width(203.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(14.dp))
        )
    }
}