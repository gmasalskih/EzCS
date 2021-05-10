package ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.widgets

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader

@Composable
fun GrenadePracticeDetailsVideoItem(
    video: Deferred<String>
) {
    Text(video.getCompleted())
}

@Composable
fun GrenadePracticeDetailsImageItem(
    image: Deferred<Bitmap>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ImageLoader(
            modifier = Modifier
                .fillMaxSize(),
            deferredBitmap = image,
            contentScale = ContentScale.FillWidth
        )
    }
}
