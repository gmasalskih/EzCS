package ru.gmasalskikh.ezcs.screens.ranks.wingman

import ru.gmasalskikh.ezcs.data.app_entity.Wingman
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WingmanViewState(
    val items: List<Wingman> = listOf(),
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState