package ru.gmasalskikh.ezcs.screens.ranks.danger_zone.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader

@Composable
fun DangerZoneItem(
    colorProgressIndicator: Color,
    name:String,
    contentDescription:String,
    deferredBitmap: Deferred<Bitmap>
) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageLoader(
            modifier = Modifier.size(width = 100.dp, height = 40.dp),
            colorProgressIndicator = colorProgressIndicator,
            contentDescription = contentDescription,
            deferredBitmap = deferredBitmap
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp)
        )
        Text(text = name)
    }
}