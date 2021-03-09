package ru.gmasalskikh.ezcs.screens.ranks.profile_rank.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Deferred
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import ru.gmasalskikh.ezcs.ui.theme.fontSize10Sp

@Composable
fun ProfileRankItem(
    colorProgressIndicator: Color,
    name:String,
    contentDescription:String,
    xp: Int,
    deferredBitmap: Deferred<Bitmap>
) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageLoader(
            modifier = Modifier.size(width = 100.dp, height = 100.dp),
            colorProgressIndicator = colorProgressIndicator,
            contentDescription = contentDescription,
            deferredBitmap = deferredBitmap
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp)
        )
        Column {
            Text(text = name)
            Text(
                text = "${xp}xp",
                fontSize = fontSize10Sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}