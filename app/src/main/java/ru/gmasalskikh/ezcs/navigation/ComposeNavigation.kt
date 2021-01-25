package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.ui.widget.AppBackground

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TargetNavigation.PREVIEW.name
    ) {
        composable(TargetNavigation.MAIN_MENU.name) {
            AppBackground(false) {
                SplashScreenView(navController = navController)
            }
        }
        composable(TargetNavigation.PREVIEW.name) {
            AppBackground(true) {
                PreviewView()
//                SplashScreenView(navController = navController)
            }
        }
        composable("") {
        }
    }
}