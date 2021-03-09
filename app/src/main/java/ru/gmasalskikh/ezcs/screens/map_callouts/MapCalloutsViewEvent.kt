package ru.gmasalskikh.ezcs.screens.map_callouts

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class MapCalloutsViewEvent : ViewEvent {
    data class NavigateTo(
        val mapHolder: MapHolder
    ) : MapCalloutsViewEvent()
}