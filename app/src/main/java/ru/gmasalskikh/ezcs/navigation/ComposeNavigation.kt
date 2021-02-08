package ru.gmasalskikh.ezcs.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import org.koin.androidx.compose.getViewModel
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.ranks.Ranks
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.ui.common_widget.AppBackground
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@SuppressLint("RestrictedApi")
@KoinApiExtension
@Composable
fun ComposeNavigation() {
    NavHost(
        navController = AmbientNavController.current,
        startDestination = TargetNavigation.SplashScreen.path
    ) {
        composable(TargetNavigation.SplashScreen.path) {
            SplashScreenView(getViewModel()).Screen()
        }
        composable(TargetNavigation.Preview.path) {
            PreviewView(getViewModel()).Screen()
        }
        composable(TargetNavigation.MainMenu.path) {
            MainMenuView(getViewModel()).Screen()
        }
        composable(TargetNavigation.Ranks.path) {
            AppBackground(true) {
                Ranks()
            }
        }
    }
}