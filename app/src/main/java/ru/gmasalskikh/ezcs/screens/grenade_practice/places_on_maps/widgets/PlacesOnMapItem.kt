package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader
import java.util.*

@Composable
fun PlacesOmMapItemContent(
    mapPoint: MapPoint,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                deferredBitmap = mapPoint.previewStartDeferred,
                contentScale = ContentScale.FillWidth
            )
            ImageLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                deferredBitmap = mapPoint.previewEndDeferred,
                contentScale = ContentScale.FillWidth
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            text = mapPoint.name.toUpperCase(Locale.getDefault())
        )
    }
}
