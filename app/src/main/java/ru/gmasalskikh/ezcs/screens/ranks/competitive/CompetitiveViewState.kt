package ru.gmasalskikh.ezcs.screens.ranks.competitive

import ru.gmasalskikh.ezcs.data.app_entity.Competitive
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class CompetitiveViewState(
    val items: List<Competitive> = listOf(),
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState