package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import ru.gmasalskikh.ezcs.screens.app_screen.AppState

class PreviewStrategy(
    override val appState: AppState
) : AppStateStrategy() {

    override fun applyStrategy() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = true,
        appTopBarState = AppState.AppTopBarState.NoAppTopBar,
        appBottomBarState = AppState.AppBottomBarState.NoAppBottomBar
    )
}