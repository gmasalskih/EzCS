package ru.gmasalskikh.ezcs.ui.common_widget

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    colorProgressIndicator: Color = LocalAppTheme.current.colors.primary,
    contentDescription: String? = null,
    deferredBitmap: Deferred<Bitmap>
) {
    var bitmap: Bitmap? by remember(deferredBitmap) { mutableStateOf(null) }
    val scope = rememberCoroutineScope()
    SideEffect { if (bitmap == null) scope.launch { bitmap = deferredBitmap.await() } }
    Box(
        modifier = modifier
    ) {
        bitmap?.let { bitmap ->
            Image(
                modifier = Modifier.fillMaxSize(),
                bitmap = bitmap.asImageBitmap(),
                contentDescription = contentDescription
            )
        } ?: CircularProgressIndicator(color = colorProgressIndicator)
    }
}