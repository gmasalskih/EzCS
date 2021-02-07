package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.runtime.*
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

object AppStateHolderImpl : AppStateHolder {
    override var appState: AppState by mutableStateOf(AppState())
        private set

    override fun setTargetNavigation(targetNavigation: TargetNavigation) {
        when (targetNavigation) {
            is TargetNavigation.SplashScreen -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("SplashScreen"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.Preview -> {
                appState = appState.copy(
                    isDrawerEnable = false,
                    appBarState = AppState.AppBarState.AppBar("Preview"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.MainMenu -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("MainMenu"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.Ranks -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("Ranks"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
        }
    }

}