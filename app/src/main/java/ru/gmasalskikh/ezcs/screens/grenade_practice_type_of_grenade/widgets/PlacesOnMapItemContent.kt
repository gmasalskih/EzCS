package ru.gmasalskikh.ezcs.screens.grenade_practice_type_of_grenade.widgets

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.ui.common_widget.ImageLoader

@Composable
fun PlacesOnMapItemContent(mapHolder: MapHolder) {
    ImageLoader(deferredBitmap = mapHolder.wallpaperDeferred)
}