package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.BottomBarState.*
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBarState.AppBar.AppBarNavContentType.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<AppViewEvent>()
    override val appViewEventEmitter: FlowCollector<AppViewEvent>
        get() = _appViewEvent

    private val _stateChangeFromNavEvent: MutableSharedFlow<NavEvent> = MutableSharedFlow()
    override val appStateChangeEmitter: FlowCollector<NavEvent>
        get() = _stateChangeFromNavEvent

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
        scaffoldState = AmbientScaffoldState.current
    }

    private fun subscribeToNavEvent() = cs.launch {
        _stateChangeFromNavEvent.collect { event ->
            scaffoldState.drawerState.close()
            appState = when (event.targetNavigationPath) {
                TargetNavigationPath.SPLASH_SCREEN -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = false,
                        appBarState = NoAppBar,
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.PREVIEW -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = NoAppBar,
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.MAIN_MENU -> {
                    appState.copy(
                        drawerGesturesEnabled = true,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(titleRes = R.string.app_bar_title_menu, MENU),
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.MAP_CALLOUTS -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(
                            titleRes = R.string.app_bar_title_map_callouts,
                            ARROW_BACK
                        ),
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(
                            titleRes = R.string.app_bar_title_weapon_characteristics,
                            ARROW_BACK
                        ),
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.GRENADES_PRACTICE -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(
                            titleRes = R.string.app_bar_title_grenades_practice,
                            ARROW_BACK
                        ),
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.RANKS -> {
                    appState.copy(
                        drawerGesturesEnabled = false,
                        isAppBackgroundBlur = true,
                        appBarState = AppBar(titleRes = R.string.app_bar_title_ranks, ARROW_BACK),
                        bottomBarState = NoBottomBar
                    )
                }
                TargetNavigationPath.BACK -> {
                    appState.copy()
                }
            }
        }
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->
            when (event) {
                AppViewEvent.OnBackClick -> {
                    navEventEmitter.emit(TargetNavigation.Back)
                }
                AppViewEvent.OnMenuClick -> {
                    if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                    else scaffoldState.drawerState.open()
                }
            }
        }
    }
}

