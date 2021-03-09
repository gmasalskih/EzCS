package ru.gmasalskikh.ezcs.screens.map_callouts.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import ru.gmasalskikh.ezcs.ui.theme.fontSize20Sp
import ru.gmasalskikh.ezcs.R
import java.util.*

@Composable
fun MapCalloutsItemContent(
    mapHolder: MapHolder,
) {
    ImageLoader(
        modifier = Modifier.fillMaxSize(),
        deferredBitmap = mapHolder.wallpaperDeferred,
        contentDescription = mapHolder.wallpaperDescription,
        contentScale = ContentScale.FillWidth
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        ImageLoader(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .aspectRatio(1f)
                .align(Alignment.Center),
            deferredBitmap = mapHolder.logoDeferred,
            contentDescription = mapHolder.logoDescription
        )
        if (mapHolder.isCompetitive) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.icon_competitive),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = mapHolder.name.toUpperCase(Locale.getDefault()),
            fontSize = fontSize20Sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
    }
}