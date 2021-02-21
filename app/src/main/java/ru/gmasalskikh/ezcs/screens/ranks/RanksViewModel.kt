package ru.gmasalskikh.ezcs.screens.ranks

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class RanksViewModel : BaseViewModel<RanksViewState, RanksViewEvent>() {

    override val container: Container<RanksViewState, SideEffect> = container(
        initialState = RanksViewState()
    )

    override suspend fun onViewEvent(viewEvent: RanksViewEvent) {

    }

}