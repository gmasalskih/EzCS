package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import ru.gmasalskikh.ezcs.data.app_entity.ProfileRank
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class ProfileRankViewState(
    val items: List<ProfileRank> = listOf(),
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState