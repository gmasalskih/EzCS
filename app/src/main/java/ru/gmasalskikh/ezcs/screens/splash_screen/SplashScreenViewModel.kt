package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.utils.IS_LAUNCH_FIRST_TIME

class SplashScreenViewModel(
    private val sharedPreferences: SharedPreferences,
    private val appEventEmitter: FlowCollector<AppController.AppEvent>,
) : BaseViewModel<SplashScreenViewState, SplashScreenViewEvent>() {

    override val container: Container<SplashScreenViewState, SideEffect> = container(
        initialState = SplashScreenViewState()
    )

    override suspend fun onViewEvent(viewEvent: SplashScreenViewEvent) {
        when (viewEvent) {
            SplashScreenViewEvent.NavigateNext -> {
                val navParams = NavigationParams(
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(TargetNavigation.SplashScreen.navId, true)
                        .build()
                )
                if (sharedPreferences.getBoolean(IS_LAUNCH_FIRST_TIME, true)) {
                    sharedPreferences.edit().putBoolean(IS_LAUNCH_FIRST_TIME, false).apply()
                    appEventEmitter.emit(
                        AppController.AppEvent.NavigateTo(
                            TargetNavigation.Preview(params = navParams)
                        )
                    )
                } else {
                    appEventEmitter.emit(
                        AppController.AppEvent.NavigateTo(
                            TargetNavigation.MainMenu(params = navParams)
                        )
                    )
                }
            }
        }
    }
}