package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class WeaponCharacteristicsPistolStrategy(
    override val appViewState: AppViewState
) : AppStateStrategy() {

    override fun applyStrategy() = getAppStateWithNewTopBarTitle(
        AppViewState.StringResourceType.StringIdRes(
            R.string.app_top_bar_title_weapon_characteristics_pistol
        )
    )
}
