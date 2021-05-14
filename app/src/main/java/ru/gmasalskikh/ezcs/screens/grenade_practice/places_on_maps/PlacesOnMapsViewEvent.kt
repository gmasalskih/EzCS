package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps

import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class PlacesOnMapsViewEvent : ViewEvent {
    data class NavigateTo(
        val mapPoint: MapPoint
    ) : PlacesOnMapsViewEvent()
}