package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.data.types.ScreenType
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.ScreenState
import ru.gmasalskikh.ezcs.utils.IS_LAUNCH_FIRST_TIME

class SplashScreenViewModel(
    private val sharedPreferences: SharedPreferences,
) : BaseViewModel<SplashScreenViewState>() {

    override val currentTargetNavigation: TargetNavigation = TargetNavigation.SplashScreen

    override val defaultViewState: SplashScreenViewState = SplashScreenViewState()

    override var screenState: ScreenState<SplashScreenViewState> by mutableStateOf(
        ScreenState(
            screenType = ScreenType.FullScreen,
            viewStateType = ViewStateType.Data,
            viewState = defaultViewState
        )
    )

    fun navigate(navController: NavController) {
        customViewModelScope.launch {  }
        if (sharedPreferences.getBoolean(IS_LAUNCH_FIRST_TIME, true)) {
            sharedPreferences.edit().putBoolean(IS_LAUNCH_FIRST_TIME, false).apply()
            navController.navigate(
                TargetNavigation.Preview.navId,
                null,
                NavOptions.Builder()
                    .setPopUpTo(TargetNavigation.SplashScreen.navId, true)
                    .build()
            )
        } else {
            navController.navigate(
                TargetNavigation.MainMenu.navId,
                null,
                NavOptions.Builder()
                    .setPopUpTo(TargetNavigation.SplashScreen.navId, true)
                    .build()
            )
        }
    }
}