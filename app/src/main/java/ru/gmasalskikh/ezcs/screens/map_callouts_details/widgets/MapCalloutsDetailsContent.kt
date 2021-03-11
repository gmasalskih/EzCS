package ru.gmasalskikh.ezcs.screens.map_callouts_details.widgets

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import kotlin.math.roundToInt

@Composable
fun MapCalloutsDetailsContent(
    mapHolder: MapHolder
) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var rotate by remember { mutableStateOf(0f) }
    ImageLoader(
        modifier = Modifier
            .rotate(rotate)
            .scale(scaleX = scale, scaleY = scale)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    rotate += rotation
                    offsetX += pan.x
                    offsetY += pan.y
                    (scale * zoom).let { scaleRes ->
                        if (scaleRes >= 1 && scaleRes < 5) scale = scaleRes
                    }
                }
            }
            .fillMaxSize(),
        deferredBitmap = mapHolder.mapDeferred,
        contentDescription = mapHolder.mapDescription
    )
}