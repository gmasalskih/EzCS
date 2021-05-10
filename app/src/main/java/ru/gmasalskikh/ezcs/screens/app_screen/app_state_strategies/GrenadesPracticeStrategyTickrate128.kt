package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class GrenadesPracticeStrategyTickrate128(
    override val appViewState: AppViewState,
    private val mapName: String?,
    private val grenadeTypeName: String?
) : AppStateStrategy() {

    override fun applyStrategy(): AppViewState = getAppStateWithNewTopBarTitle(
        AppViewState.StringResourceType.StringNative(
            res = "$mapName $grenadeTypeName"
        )
    )
}