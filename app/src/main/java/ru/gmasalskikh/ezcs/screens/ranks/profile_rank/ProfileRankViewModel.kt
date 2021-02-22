package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class ProfileRankViewModel : BaseViewModel<ProfileRankViewState, Nothing>() {

    override val container: Container<ProfileRankViewState, SideEffect> = container(
        initialState = ProfileRankViewState()
    )
}