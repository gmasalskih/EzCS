package ru.gmasalskikh.ezcs.screens.app_screen

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Providers
import androidx.navigation.compose.rememberNavController
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.AppScreen
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.widgets.*

@KoinApiExtension
class AppView {

    private val appStateHolder: AppStateHolder = AppStateHolderImpl

    @Composable
    fun Render(stateHolder: AppStateHolder = appStateHolder) {
        Providers(
            AmbientNavController provides rememberNavController(),
            AmbientScaffoldState provides rememberScaffoldState(
                drawerState = rememberDrawerState(
                    if (stateHolder.appState.isDrawerOpen) DrawerValue.Open
                    else DrawerValue.Closed
                )
            ),
            AmbientAppStateHolder provides stateHolder
        ) {
            stateHolder.SetComposableScope()
            DisposableEffect(key1 = Unit) {
                stateHolder.onViewCreate()
                onDispose {
                    stateHolder.onViewDestroy()
                }
            }
            AppScreen(
                isAppBackgroundBlur = stateHolder.appState.isAppBackgroundBlur,
                topBar = { AppBar() },
                drawerGesturesEnabled = stateHolder.appState.isDrawerEnable,
            )
        }
    }
}

