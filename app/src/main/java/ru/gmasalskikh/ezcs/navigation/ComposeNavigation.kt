package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.screens.ranks.RanksView
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun ComposeNavigation(navigator: Navigator = get()) {
    val navController = AmbientNavController.current
    val navEventEmitter = AmbientAppStateHolder.current.stateChangeFromNavEventEmitter
    val cs = rememberCoroutineScope()
    DisposableEffect(key1 = null) {
        navigator.onAttach(navController)
        onDispose {
            navigator.onDetach()
        }
    }
    NavHost(
        navController = navController,
        startDestination = TargetNavigationPath.SPLASH_SCREEN.name
    ) {
        composable(TargetNavigationPath.SPLASH_SCREEN.name) {
            cs.launch {
                navEventEmitter.emit(
                    AppStateHolder.NavEvent(TargetNavigationPath.SPLASH_SCREEN)
                )
            }
            SplashScreenView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.PREVIEW.name) {
            cs.launch {
                navEventEmitter.emit(
                    AppStateHolder.NavEvent(TargetNavigationPath.PREVIEW)
                )
            }
            PreviewView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.MAIN_MENU.name) {
            cs.launch {
                navEventEmitter.emit(
                    AppStateHolder.NavEvent(TargetNavigationPath.MAIN_MENU)
                )
            }
            MainMenuView(getViewModel()).Screen()
        }
        composable(TargetNavigationPath.RANKS.name) {
            cs.launch {
                navEventEmitter.emit(
                    AppStateHolder.NavEvent(TargetNavigationPath.RANKS)
                )
            }
            RanksView(getViewModel()).Screen()
        }
    }
}