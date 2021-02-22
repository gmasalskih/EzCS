package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class ProfileRankViewState(
    val name: String = "ProfileRank",
    override val currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState