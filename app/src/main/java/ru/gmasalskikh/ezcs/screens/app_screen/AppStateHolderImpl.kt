package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.utils.AmbientNavController
import kotlin.coroutines.CoroutineContext
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.BottomBarState.*
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.AppBar.AppBarNavContentType.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<ViewEvent>()
    override val viewEventEmitter: FlowCollector<ViewEvent>
        get() = _appViewEvent

    private val _navEvent: MutableSharedFlow<TargetNavigation> = MutableSharedFlow()
    override val navEventEmitter: FlowCollector<TargetNavigation>
        get() = _navEvent

    private lateinit var navController: NavHostController
    private lateinit var scaffoldState: ScaffoldState

    override var appState: AppState by mutableStateOf(AppState())
        private set

    override fun onViewCreate() {
        cs.onStart()
        subscribeToViewEvent()
        subscribeToNavEvent()
    }

    override fun onViewDestroy() {
        cs.onStop()
    }

    @Composable
    override fun SetComposableScope() {
        navController = AmbientNavController.current
        scaffoldState = AmbientScaffoldState.current
    }

    private fun subscribeToNavEvent() = cs.launch {
        _navEvent.collect { event ->
            scaffoldState.drawerState.close()
            when (event) {
                is TargetNavigation.SplashScreen -> {
                    appState = appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = false,
                        appBarState = AppBarGone,
                        bottomBarState = BottomBarGone
                    )
                }
                is TargetNavigation.Preview -> {
                    appState = appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = AppBarGone,
                        bottomBarState = BottomBarGone
                    )
                }
                is TargetNavigation.MainMenu -> {
                    appState = appState.copy(
                        drawerGesturesEnabled = true,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(titleRes = R.string.app_bar_title_menu, MENU),
                        bottomBarState = BottomBarGone
                    )
                }
                is TargetNavigation.Ranks -> {
                    appState = appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = false,
                        appBarState = AppBar(titleRes = R.string.app_bar_title_ranks, ARROW_BACK),
                        bottomBarState = BottomBarGone
                    )
                }
            }
        }
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->
            when (event) {
                ViewEvent.OnBackClick -> {
                    navController.popBackStack()
                }
                ViewEvent.OnMenuClick -> {
                    if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                    else scaffoldState.drawerState.open()
                }
            }
        }
    }
}