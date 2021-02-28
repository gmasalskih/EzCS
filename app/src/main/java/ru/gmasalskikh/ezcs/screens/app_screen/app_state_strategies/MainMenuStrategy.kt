package ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies

import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.app_screen.AppViewState

class MainMenuStrategy(
    override val appViewState: AppViewState,
    private val scaffoldState: ScaffoldState
) : AppStateStrategy() {

    override fun applyStrategy() = appViewState.copy(
        drawerGesturesEnabled = true,
        isAppBackgroundBlur = true,
        appTopBarState = AppViewState.AppTopBarState.AppTopBar(
            titleRes = R.string.app_top_bar_title_menu,
            appTopBarNavItem = AppViewState.AppTopBarNavItem(
                icon = Icons.Filled.Menu,
                onClick = {
                    if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                    else scaffoldState.drawerState.open()
                }
            )
        ),
        appBottomBarState = AppViewState.AppBottomBarState.NoAppBottomBar
    )
}