package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class SplashScreenStrategy(
    override val appViewState: AppViewState
) : AppStateStrategy() {

    override fun applyStrategy() = appViewState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = false,
        appTopBarState = AppViewState.AppTopBarState.NoAppTopBar,
        appBottomBarState = AppViewState.AppBottomBarState.NoAppBottomBar
    )
}