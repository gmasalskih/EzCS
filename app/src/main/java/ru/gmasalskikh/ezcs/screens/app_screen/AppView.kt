package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.get
import ru.gmasalskikh.ezcs.utils.LocalNavController
import ru.gmasalskikh.ezcs.utils.LocalScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.AppScreen
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.*
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.AppDrawer

@Composable
fun AppView(stateHolder: AppStateHolder = get()) {
    val appState = stateHolder.appViewState
    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
        LocalScaffoldState provides rememberScaffoldState(),
    ) {
        stateHolder.setScaffoldState(LocalScaffoldState.current)
        DisposableEffect(key1 = null) {
            stateHolder.onViewCreate()
            onDispose {
                stateHolder.onViewDestroy()
            }
        }
        AppScreen(
            isAppBackgroundBlur = appState.isAppBackgroundBlur,
            topBar = { AppTopBar(appState.appTopBarState) },
            drawerGesturesEnabled = appState.drawerGesturesEnabled,
            drawerContent = { AppDrawer() },
            bottomBar = { AppBottomBar(appState.appBottomBarState) }
        )
    }
}
