package ru.gmasalskikh.ezcs.screens.grenades_practice

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class GrenadesPracticeViewModel :
    BaseViewModel<GrenadesPracticeViewState, GrenadesPracticeViewEvent>() {

    override val container: Container<GrenadesPracticeViewState, SideEffect> = container(
        initialState = GrenadesPracticeViewState()
    )

    override suspend fun onViewEvent(viewEvent: GrenadesPracticeViewEvent) {
    }
}