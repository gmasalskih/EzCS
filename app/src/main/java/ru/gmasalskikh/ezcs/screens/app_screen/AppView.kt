package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Providers
import androidx.navigation.compose.rememberNavController
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.AppScreen
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.*

class AppView {

    private val appStateHolder: AppStateHolder = AppStateHolderImpl

    @Composable
    fun Render(stateHolder: AppStateHolder = appStateHolder) {
        Providers(
            AmbientNavController provides rememberNavController(),
            AmbientScaffoldState provides rememberScaffoldState(),
            AmbientAppStateHolder provides stateHolder
        ) {
            stateHolder.SetComposableScope()
            DisposableEffect(key1 = null) {
                stateHolder.onViewCreate()
                onDispose {
                    stateHolder.onViewDestroy()
                }
            }
            AppScreen(
                isAppBackgroundBlur = stateHolder.appState.isAppBackgroundBlur,
                topBar = { AppBar() },
                drawerGesturesEnabled = stateHolder.appState.drawerGesturesEnabled,
            )
        }
    }
}

