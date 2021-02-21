package ru.gmasalskikh.ezcs.screens.map_callouts

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class MapCalloutsViewModel:BaseViewModel<MapCalloutsViewState, MapCalloutsViewEvent>() {

    override val container: Container<MapCalloutsViewState, SideEffect> = container(
        initialState = MapCalloutsViewState()
    )

    override suspend fun onViewEvent(viewEvent: MapCalloutsViewEvent) {
    }
}