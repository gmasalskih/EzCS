package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import kotlin.coroutines.CoroutineContext
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.BottomBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.AppBar.AppBarNavContentType.*
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState

object AppStateHolderImpl : AppStateHolder {

    override val appViewEvent = MutableSharedFlow<AppViewEvent>()
    override val navEvent: MutableStateFlow<TargetNavigation> =
        MutableStateFlow(TargetNavigation.SplashScreen)

    private lateinit var navController: NavHostController
    private lateinit var scaffoldState: ScaffoldState

    override var appState: AppState by mutableStateOf(AppState())
        private set

    private val cs = object : AppStateHolderCoroutineScope {
        var job: Job = Job()
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + job

        override fun onStart() {
            job = Job()
        }

        override fun onStop() {
            job.cancel()
        }
    }

    private interface AppStateHolderCoroutineScope : CoroutineScope {
        fun onStart()
        fun onStop()
    }


    override fun setTargetNavigation(targetNavigation: TargetNavigation) {
        when (targetNavigation) {
            is TargetNavigation.SplashScreen -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppBarGone,
                    bottomBarState = BottomBarGone
                )
            }
            is TargetNavigation.Preview -> {
                appState = appState.copy(
                    isDrawerEnable = false,
                    appBarState = AppBarGone,
                    bottomBarState = BottomBarGone
                )
            }
            is TargetNavigation.MainMenu -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppBar(title = "MainMenu", navContentType = MENU),
                    bottomBarState = BottomBarGone
                )
            }
            is TargetNavigation.Ranks -> {
                appState = appState.copy(
                    isDrawerEnable = true,
                    appBarState = AppBar(title = "Ranks", navContentType = ARROW_BACK),
                    bottomBarState = BottomBarGone
                )
            }
        }
    }

    override suspend fun navigateTo(targetNavigation: TargetNavigation) {

    }

    @Composable
    override fun SetComposableScope() {
        navController = AmbientNavController.current
        scaffoldState = AmbientScaffoldState.current
    }


    override fun onViewCreate() {
        cs.onStart()
        subscribeToViewEvent()
        subscribeToNavEvent()
    }

    private fun subscribeToNavEvent() {
        cs.launch {
            navEvent.collect { event ->
                when (event) {
                    TargetNavigation.SplashScreen -> {
                        appState = appState.copy(
                            isDrawerEnable = true,
                            appBarState = AppBar(title = "SplashScreen", MENU),
                            bottomBarState = BottomBarGone
                        )

                    }
                    TargetNavigation.Preview -> TODO()
                    TargetNavigation.MainMenu -> TODO()
                    TargetNavigation.Ranks -> TODO()
                }
            }
        }
    }

    private fun subscribeToViewEvent() {
        cs.launch {
            appViewEvent.collect { event ->
                when (event) {
                    AppViewEvent.OnBackClick -> {
                        navController.popBackStack()
                    }
                    AppViewEvent.OnMenuClick -> {
                        if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                        else scaffoldState.drawerState.open()
                    }
                    AppViewEvent.Nop -> Unit
                }
            }
        }
    }

    override fun onViewDestroy() {
        cs.onStop()
    }

}