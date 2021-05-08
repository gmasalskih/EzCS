package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategyTickrate128(
    override val appViewState: AppViewState
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState = getAppStateWithNewTopBarTitle(
        AppViewState.StringResourceType.StringIdRes(
            res = R.string.app_top_bar_title_grenades_practice_type_of_grenade_smoke
        )
    )
}