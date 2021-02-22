package ru.gmasalskikh.ezcs.screens.ranks.profile_rank

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.ranks.profile_rank.widgets.ProfileRankContent

class ProfileRankView(
    vm: ProfileRankViewModel
) : BaseView<ProfileRankViewState, Nothing, ProfileRankViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: ProfileRankViewState) {
        ProfileRankContent(text = viewState.name)
    }
}