package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.utils.IS_LAUNCH_FIRST_TIME

class SplashScreenViewModel(
    private val sharedPreferences: SharedPreferences,
    private val navEventEmitter: FlowCollector<TargetNavigation>,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<SplashScreenViewState>(
    defaultViewState = SplashScreenViewState(),
    initViewStateType = ViewStateType.Data,
    savedStateHandle = savedStateHandle
) {
    fun navigate() = viewModelScope.launch {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigationPath.SPLASH_SCREEN.navId, true)
                .build()
        )
        if (sharedPreferences.getBoolean(IS_LAUNCH_FIRST_TIME, true)) {
            sharedPreferences.edit().putBoolean(IS_LAUNCH_FIRST_TIME, false).apply()
            navEventEmitter.emit(TargetNavigation.Preview(params = navParams))
        } else {
            navEventEmitter.emit(TargetNavigation.MainMenu(params = navParams))
        }
    }

}