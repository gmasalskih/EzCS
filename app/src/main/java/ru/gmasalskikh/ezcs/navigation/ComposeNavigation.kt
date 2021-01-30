package ru.gmasalskikh.ezcs.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.ui.widget.AppBackground
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@SuppressLint("RestrictedApi")
@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    Providers(AmbientNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = TargetNavigation.PREVIEW.name
        ) {
            composable(TargetNavigation.SPLASH_SCREEN.name) {
                AppBackground(false) {
                    SplashScreenView()
                }
            }
            composable(TargetNavigation.PREVIEW.name) {
                AppBackground(true) {
                    PreviewView()
                }
            }
            composable(TargetNavigation.MAIN_MENU.name) {
                AppBackground(true) {
                    MainMenuView()
                }
            }
        }
    }
}