package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl

class NavigationEventBusImpl(
    private val appStateHolder: AppStateHolder
) : NavigationEventBus {

    private lateinit var navController: NavController
    private var job: Job? = null
    private val cs: CoroutineScope = CoroutineScope(Dispatchers.Main)
    override val navigationEventBus: MutableStateFlow<TargetNavigation?> = MutableStateFlow(null)

    override fun setNavController(navController: NavController) {
        this.navController = navController
        job?.cancel()
        job = cs.launch {
//            navigationEventBus.collect { targetNavigation ->
//                when (targetNavigation) {
//                    is TargetNavigation.SplashScreen -> {
//                        appStateHolder.setNewAppState(
//                            appStateHolder.appState.copy(
//                                isDrawerEnable = true,
//                                appBarState = AppState.AppBarState.AppBar("SplashScreen"),
//                                bottomBarState = AppState.BottomBarState.BottomBarGone
//                            )
//                        )
//                    }
//                    is TargetNavigation.Preview -> {
//                        AppStateHolderImpl.appState = AppStateHolderImpl.appState.copy(
//                            isDrawerEnable = false,
//                            appBarState = AppState.AppBarState.AppBar("Preview"),
//                            bottomBarState = AppState.BottomBarState.BottomBarGone
//                        )
//                    }
//                    is TargetNavigation.MainMenu -> {
//                        AppStateHolderImpl.appState = AppStateHolderImpl.appState.copy(
//                            isDrawerEnable = true,
//                            appBarState = AppState.AppBarState.AppBar("MainMenu"),
//                            bottomBarState = AppState.BottomBarState.BottomBarGone
//                        )
//                    }
//                    is TargetNavigation.Ranks -> {
//                        AppStateHolderImpl.appState = AppStateHolderImpl.appState.copy(
//                            isDrawerEnable = true,
//                            appBarState = AppState.AppBarState.AppBar("Ranks"),
//                            bottomBarState = AppState.BottomBarState.BottomBarGone
//                        )
//                    }
//                }
//            }
        }

    }
}