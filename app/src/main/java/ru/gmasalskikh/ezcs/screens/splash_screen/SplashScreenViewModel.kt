package ru.gmasalskikh.ezcs.screens.splash_screen

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel

class SplashScreenViewModel(
    private val sharedPreferences: SharedPreferences,
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : BaseViewModel<SplashScreenViewState>(
    defaultViewState = SplashScreenViewState(),
    initViewStateType = ViewStateType.Data
) {

    fun navigate() {
        viewModelScope.launch {
            navEventEmitter.emit(
                TargetNavigation.Preview(
                    params = NavigationParams(
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(TargetNavigation.SplashScreen().navId, true)
                            .build()
                    )
                )
            )
        }
//        if (sharedPreferences.getBoolean(IS_LAUNCH_FIRST_TIME, true)) {
//            sharedPreferences.edit().putBoolean(IS_LAUNCH_FIRST_TIME, false).apply()
//            navController.navigate(
//                TargetNavigation.Preview.navId,
//                null,
//                NavOptions.Builder()
//                    .setPopUpTo(TargetNavigation.SplashScreen.navId, true)
//                    .build()
//            )
//        } else {
//            navController.navigate(
//                TargetNavigation.MainMenu.navId,
//                null,
//                NavOptions.Builder()
//                    .setPopUpTo(TargetNavigation.SplashScreen.navId, true)
//                    .build()
//            )
//        }
    }
}