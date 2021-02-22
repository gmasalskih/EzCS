package ru.gmasalskikh.ezcs.screens.ranks.competitive

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class CompetitiveViewModel : BaseViewModel<CompetitiveViewState, Nothing>() {
    override val container: Container<CompetitiveViewState, SideEffect> = container(
        initialState = CompetitiveViewState()
    )
}