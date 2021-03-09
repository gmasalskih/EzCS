package ru.gmasalskikh.ezcs.screens.map_callouts_details

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class MapCalloutsDetailsViewModel : BaseViewModel<MapCalloutsDetailsViewState, Nothing>() {

    override val container: Container<MapCalloutsDetailsViewState, SideEffect> = container(
        initialState = MapCalloutsDetailsViewState()
    )




}