package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class DangerZoneViewModel : BaseViewModel<DangerZoneViewState, Nothing>() {

    override val container: Container<DangerZoneViewState, SideEffect> = container(
        initialState = DangerZoneViewState()
    )
}