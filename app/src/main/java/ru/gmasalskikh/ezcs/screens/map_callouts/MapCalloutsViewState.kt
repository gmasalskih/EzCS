package ru.gmasalskikh.ezcs.screens.map_callouts

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class MapCalloutsViewState(
    val maps: List<MapHolder> = listOf(),
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState