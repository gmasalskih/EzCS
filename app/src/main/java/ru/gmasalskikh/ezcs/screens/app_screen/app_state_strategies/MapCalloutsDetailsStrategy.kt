package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class MapCalloutsDetailsStrategy(
    override val appViewState: AppViewState,
    private val topAppBarTitle: String
) : AppStateStrategy() {

    override fun applyStrategy() = getAppStateWithNewTopBarTitle(
        AppViewState.StringResourceType.StringNative(topAppBarTitle)
    )
}