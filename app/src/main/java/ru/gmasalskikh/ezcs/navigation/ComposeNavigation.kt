package ru.gmasalskikh.ezcs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.parameter.parametersOf
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuView
import ru.gmasalskikh.ezcs.screens.preview.PreviewView
import ru.gmasalskikh.ezcs.screens.ranks.Ranks
import ru.gmasalskikh.ezcs.screens.splash_screen.SplashScreenView
import ru.gmasalskikh.ezcs.ui.common_widget.AppBackground
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@KoinApiExtension
@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    Providers(AmbientNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = TargetNavigation.SPLASH_SCREEN.name
        ) {
            composable(TargetNavigation.SPLASH_SCREEN.name) {
                AppBackground(false) {
                    SplashScreenView(getViewModel { parametersOf(it.destination.id) }).Screen()
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
            composable(TargetNavigation.RANKS.name) {
                AppBackground(true) {
                    Ranks()
                }
            }
        }
    }
}