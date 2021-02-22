package ru.gmasalskikh.ezcs.screens.ranks.wingman

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WingmanViewState(
    val name: String = "Wingman",
    override val currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState