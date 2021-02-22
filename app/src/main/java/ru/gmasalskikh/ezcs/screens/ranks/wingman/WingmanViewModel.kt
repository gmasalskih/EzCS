package ru.gmasalskikh.ezcs.screens.ranks.wingman

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WingmanViewModel : BaseViewModel<WingmanViewState, Nothing>() {

    override val container: Container<WingmanViewState, SideEffect> = container(
        initialState = WingmanViewState()
    )
}