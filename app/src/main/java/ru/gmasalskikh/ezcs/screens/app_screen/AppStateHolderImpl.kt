package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppTopBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBottomBarState.*
import ru.gmasalskikh.ezcs.utils.AmbientScaffoldState
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppTopBarState.AppTopBar.AppTopBarNavContentType.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
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

    private fun getSplashScreenState() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = false,
        appTopBarState = NoAppTopBar,
        appBottomBarState = NoAppBottomBar
    )

    private fun getPreviewState() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = true,
        appTopBarState = NoAppTopBar,
        appBottomBarState = NoAppBottomBar
    )

    private fun getMainMenuState() = appState.copy(
        drawerGesturesEnabled = true,
        isAppBackgroundBlur = true,
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_menu,
            MENU
        ),
        appBottomBarState = NoAppBottomBar
    )

    private fun getMapCalloutsState() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = true,
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_map_callouts,
            ARROW_BACK
        ),
        appBottomBarState = NoAppBottomBar
    )

    private fun getWeaponCharacteristicsState() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = true,
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_weapon_characteristics,
            ARROW_BACK
        ),
        appBottomBarState = NoAppBottomBar
    )

    private fun getGrenadesPracticeState() = appState.copy(
        drawerGesturesEnabled = false,
        isAppBackgroundBlur = true,
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_grenades_practice,
            ARROW_BACK
        ),
        appBottomBarState = NoAppBottomBar
    )

    private fun getRanksState(): AppState {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigationPath.RANKS.navId, true)
                .build()
        )

        fun onClickBottomBarRanksItem(targetNavigation: TargetNavigation) = cs.launch {
            navEventEmitter.emit(targetNavigation)
        }
        return appState.copy(
            drawerGesturesEnabled = false,
            isAppBackgroundBlur = true,
            appBottomBarState = AppBottomBar(
                listOf(
                    AppState.AppBottomBarItem(
                        itemName = R.string.app_bottom_bar_ranks_competitive,
                        itemIcon = R.drawable.icon_competitive,
                        route = TargetNavigationPath.RANKS_COMPETITIVE,
                        onClick = {
                            onClickBottomBarRanksItem(
                                TargetNavigation.RanksCompetitive(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        itemName = R.string.app_bottom_bar_ranks_wingman,
                        itemIcon = R.drawable.icon_wingman,
                        route = TargetNavigationPath.RANKS_WINGMAN,
                        onClick = {
                            onClickBottomBarRanksItem(
                                TargetNavigation.RanksWingman(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        itemName = R.string.app_bottom_bar_ranks_danger_zone,
                        itemIcon = R.drawable.icon_danger_zone,
                        route = TargetNavigationPath.RANKS_DANGER_ZONE,
                        onClick = {
                            onClickBottomBarRanksItem(
                                TargetNavigation.RanksDangerZone(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        itemName = R.string.app_bottom_bar_ranks_profile_rank,
                        itemIcon = R.drawable.icon_profile_rank,
                        route = TargetNavigationPath.RANKS_PROFILE_RANK,
                        onClick = {
                            onClickBottomBarRanksItem(
                                TargetNavigation.RanksProfileRank(navParams)
                            )
                        }
                    )
                )
            )
        )
    }

    private fun getRanksCompetitiveState() = appState.copy(
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_ranks_competitive,
            ARROW_BACK
        )
    )

    private fun getRanksWingman() = appState.copy(
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_ranks_wingman,
            ARROW_BACK
        )
    )

    private fun getRanksDangerZoneState() = appState.copy(
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_ranks_danger_zone,
            ARROW_BACK
        )
    )

    private fun getRanksProfileRank() = appState.copy(
        appTopBarState = AppTopBar(
            titleRes = R.string.app_top_bar_title_ranks_profile_rank,
            ARROW_BACK
        )
    )

    private fun subscribeToNavEvent() = cs.launch {
        _stateChangeFromNavEvent.collect { event ->
            scaffoldState.drawerState.close()
            appState = when (event.targetNavigationPath) {
                TargetNavigationPath.SPLASH_SCREEN -> getSplashScreenState()
                TargetNavigationPath.PREVIEW -> getPreviewState()
                TargetNavigationPath.MAIN_MENU -> getMainMenuState()
                TargetNavigationPath.MAP_CALLOUTS -> getMapCalloutsState()
                TargetNavigationPath.WEAPON_CHARACTERISTICS -> getWeaponCharacteristicsState()
                TargetNavigationPath.GRENADES_PRACTICE -> getGrenadesPracticeState()
                TargetNavigationPath.RANKS -> getRanksState()
                TargetNavigationPath.RANKS_COMPETITIVE -> getRanksCompetitiveState()
                TargetNavigationPath.RANKS_WINGMAN -> getRanksWingman()
                TargetNavigationPath.RANKS_DANGER_ZONE -> getRanksDangerZoneState()
                TargetNavigationPath.RANKS_PROFILE_RANK -> getRanksProfileRank()
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

