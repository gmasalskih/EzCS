package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppState

class MainMenuStrategy(
    override val appState: AppState,
    private val scaffoldState: ScaffoldState
) : AppStateStrategy() {

    override fun applyStrategy() = appState.copy(
        drawerGesturesEnabled = true,
        isAppBackgroundBlur = true,
        appTopBarState = AppState.AppTopBarState.AppTopBar(
            titleRes = R.string.app_top_bar_title_menu,
            appTopBarNavItem = AppState.AppTopBarNavItem(
                icon = Icons.Filled.Menu,
                onClick = {
                    if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                    else scaffoldState.drawerState.open()
                }
            )
        ),
        appBottomBarState = AppState.AppBottomBarState.NoAppBottomBar
    )
}