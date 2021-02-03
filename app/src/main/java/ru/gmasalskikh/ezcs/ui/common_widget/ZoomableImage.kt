package ru.gmasalskikh.ezcs.ui.common_widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.zoomable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.rawDragGestureFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun ZoomableImage(
    imageBitmap: ImageBitmap,
    maxScale: Float = 5f,
    minScale: Float = 1f,
) {
    var scale by remember { mutableStateOf(1f) }
    var translation by remember { mutableStateOf(Offset(0f, 0f)) }
    Image(
        modifier = Modifier
            .fillMaxSize()
            .zoomable {
                val deltaScale = scale * it
                scale = if (deltaScale in minScale..maxScale) deltaScale else scale
            }
            .rawDragGestureFilter(
                object : DragObserver {
                    override fun onDrag(dragDistance: Offset): Offset {
                        translation = translation.plus(dragDistance)
                        return super.onDrag(dragDistance)
                    }
                }
            )
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = translation.x,
                translationY = translation.y
            ),
        bitmap = imageBitmap,
        contentDescription = null,
    )
}