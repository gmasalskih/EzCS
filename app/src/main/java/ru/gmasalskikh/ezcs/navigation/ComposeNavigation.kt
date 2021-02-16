package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.screens.ranks.RanksView
import ru.gmasalskikh.ezcs.utils.AmbientAppStateHolder
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun ComposeNavigation(navigator: Navigator = get()) {
    val navController = AmbientNavController.current
    val navEventEmitter = AmbientAppStateHolder.current.navEventEmitter
    val cs = rememberCoroutineScope()
    DisposableEffect(key1 = null) {
        navigator.onAttach(navController)
        onDispose {
            navigator.onDetach()
        }
    }
    NavHost(
        navController = navController,
        startDestination = TargetNavigation.SplashScreen().path
    ) {
        composable(TargetNavigation.SplashScreen().path) {
            cs.launch { navEventEmitter.emit(TargetNavigation.SplashScreen()) }
            SplashScreenView(getViewModel()).Screen()
        }
        composable(TargetNavigation.Preview().path) {
            cs.launch { navEventEmitter.emit(TargetNavigation.Preview()) }
            PreviewView(getViewModel()).Screen()
        }
        composable(TargetNavigation.MainMenu().path) {
            cs.launch { navEventEmitter.emit(TargetNavigation.MainMenu()) }
            MainMenuView(getViewModel()).Screen()
        }
        composable(TargetNavigation.Ranks().path) {
            cs.launch { navEventEmitter.emit(TargetNavigation.Ranks()) }
            RanksView(getViewModel()).Screen()
        }
    }
}