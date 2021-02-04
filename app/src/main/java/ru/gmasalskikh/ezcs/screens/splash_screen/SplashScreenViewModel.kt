package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.utils.IS_LAUNCH_FIRST_TIME

class SplashScreenViewModel(
    private val sp: SharedPreferences,
    viewState: SplashScreenViewState,
    navId: Int
) : BaseViewModel<SplashScreenViewState>(viewState, navId) {

    override fun onViewCreate() {
        this.
        screenState = screenState.copy(
            viewStateType = ViewStateType.Data
        )
    }

    fun navigate(navController: NavController) {
        if (sp.getBoolean(IS_LAUNCH_FIRST_TIME, true)) {
            sp.edit().putBoolean(IS_LAUNCH_FIRST_TIME, false).apply()
            navController.navigate(TargetNavigation.PREVIEW.name) {
                popUpTo(TargetNavigation.SPLASH_SCREEN.name) { inclusive = true }
            }
        } else {
            navController.navigate(TargetNavigation.MAIN_MENU.name) {
                popUpTo(TargetNavigation.SPLASH_SCREEN.name) { inclusive = true }
            }
        }
    }
}