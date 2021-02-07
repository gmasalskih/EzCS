package ru.gmasalskikh.ezcs.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.ranks.Ranks
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.ui.common_widget.AppBackground
import ru.gmasalskikh.ezcs.ui.common_widget.AppScreen
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import ru.gmasalskikh.ezcs.utils.CurrentRoute

@SuppressLint("RestrictedApi")
@KoinApiExtension
@Composable
fun ComposeNavigation(
    appStateHolder: AppStateHolder = get()
) {
    val navController = rememberNavController()
    Providers(AmbientNavController provides navController) {
        AppScreen {
            NavHost(
                navController = navController,
                startDestination = TargetNavigation.SplashScreen.path
            ) {
                composable(TargetNavigation.SplashScreen.path) {
                    appStateHolder.setTargetNavigation(TargetNavigation.SplashScreen)
                    AppBackground(false) {
                        SplashScreenView(getViewModel()).Screen()
                    }
                }
                composable(TargetNavigation.Preview.path) {
                    appStateHolder.setTargetNavigation(TargetNavigation.Preview)
                    AppBackground(true) {
                        PreviewView(getViewModel()).Screen()
                    }
                }
                composable(TargetNavigation.MainMenu.path) {
                    appStateHolder.setTargetNavigation(TargetNavigation.MainMenu)
                    AppBackground(true) {
                        MainMenuView(getViewModel()).Screen()
                    }
                }
                composable(TargetNavigation.Ranks.path) {
                    Log.d("!!!", " backStack.size ${navController.previousBackStackEntry}")
                    appStateHolder.setTargetNavigation(TargetNavigation.Ranks)
                    AppBackground(true) {
                        Ranks()
                    }
                }
            }
        }
    }
}