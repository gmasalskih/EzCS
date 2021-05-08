package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps

import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.data.type.TickrateType
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class PlacesOnMapsViewState(
    val items: List<MapPoint> = listOf(),
    val currentTickrateType: TickrateType = TickrateType.TICKRATE_64,
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState