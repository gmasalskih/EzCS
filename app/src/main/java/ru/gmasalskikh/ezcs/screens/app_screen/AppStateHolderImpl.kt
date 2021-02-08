package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.runtime.*
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import kotlin.coroutines.CoroutineContext

object AppStateHolderImpl : AppStateHolder {

    override var appState: AppState by mutableStateOf(AppState())
        private set

    override fun setNewAppState(appState: AppState) {
        this.appState = appState
    }

    private val cs = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main
    }

    override val navigationEventBus: MutableStateFlow<TargetNavigation?> = MutableStateFlow(null)

    private lateinit var navController: NavController

    override fun setTargetNavigation(targetNavigation: TargetNavigation) {
        when (targetNavigation) {
            is TargetNavigation.SplashScreen -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("SplashScreen"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.Preview -> {
                appState = appState.copy(
                    isDrawerEnable = false,
                    appBarState = AppState.AppBarState.AppBar("Preview"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.MainMenu -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("MainMenu"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
            is TargetNavigation.Ranks -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppState.AppBarState.AppBar("Ranks"),
                    bottomBarState = AppState.BottomBarState.BottomBarGone
                )
            }
        }
    }

    override suspend fun navigateTo(targetNavigation: TargetNavigation) {

    }

    @Composable
    override fun SetComposableScope() {
        navController = AmbientNavController.current
    }


}