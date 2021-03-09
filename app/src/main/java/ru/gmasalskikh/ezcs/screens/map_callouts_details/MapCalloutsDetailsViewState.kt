package ru.gmasalskikh.ezcs.screens.map_callouts_details

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class MapCalloutsDetailsViewState(
    override var currentSideEffect: SideEffect = SideEffect.Loading,
    val mapCallouts: MapHolder? = null
) : ViewState