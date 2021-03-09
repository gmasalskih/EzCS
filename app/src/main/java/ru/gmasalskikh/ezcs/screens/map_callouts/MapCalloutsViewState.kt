package ru.gmasalskikh.ezcs.screens.map_callouts

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class MapCalloutsViewState(
    val name: String = "Map Callouts",
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState