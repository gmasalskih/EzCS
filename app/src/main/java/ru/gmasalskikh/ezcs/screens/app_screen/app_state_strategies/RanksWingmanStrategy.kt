package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class RanksWingmanStrategy(
    override val appViewState: AppViewState
) : AppStateStrategy() {
    override fun applyStrategy() =
        getAppStateWithNewTopBarTitle(R.string.app_top_bar_title_ranks_wingman)
}