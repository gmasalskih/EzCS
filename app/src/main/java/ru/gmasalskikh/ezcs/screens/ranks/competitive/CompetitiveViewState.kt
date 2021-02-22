package ru.gmasalskikh.ezcs.screens.ranks.competitive

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class CompetitiveViewState(
    val name: String = "Competitive",
    override val currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState