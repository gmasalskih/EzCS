package ru.gmasalskikh.ezcs.screens.map_callouts_details.widgets

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader

@Composable
fun MapCalloutsDetailsContent(
    mapHolder: MapHolder
){
    ImageLoader(
        deferredBitmap = mapHolder.mapDeferred,
        contentDescription = mapHolder.mapDescription
    )
}