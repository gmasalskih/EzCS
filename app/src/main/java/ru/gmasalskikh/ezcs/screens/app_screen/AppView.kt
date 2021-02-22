package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Providers
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.get
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.AppScreen
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.*
import ru.gmasalskikh.ezcs.ui.common_widget.AppDrawer

@Composable
fun AppView(stateHolder: AppStateHolder = get()) {
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
            topBar = { AppTopBar() },
            drawerGesturesEnabled = stateHolder.appState.drawerGesturesEnabled,
            drawerContent = { AppDrawer() },
            bottomBar = { AppBottomBar() }
        )
    }
}
