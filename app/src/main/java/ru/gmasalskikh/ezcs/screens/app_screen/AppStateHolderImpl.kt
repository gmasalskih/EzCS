package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppTopBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppState.AppBottomBarState.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import java.lang.IllegalStateException

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>,
    private val navEventCollector: SharedFlow<TargetNavigation>
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<AppViewEvent>()
    override val appViewEventCollector: SharedFlow<AppViewEvent>
        get() = _appViewEvent.asSharedFlow()
    override val appViewEventEmitter: FlowCollector<AppViewEvent>
        get() = _appViewEvent

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

    override fun setScaffoldState(scaffoldState: ScaffoldState) {
        this.scaffoldState = scaffoldState
    }

    private fun setSplashScreenState() {
        appState = appState.copy(
            drawerGesturesEnabled = false,
            isAppBackgroundBlur = false,
            appTopBarState = NoAppTopBar,
            appBottomBarState = NoAppBottomBar
        )
    }

    private fun setPreviewState() {
        appState = appState.copy(
            drawerGesturesEnabled = false,
            isAppBackgroundBlur = true,
            appTopBarState = NoAppTopBar,
            appBottomBarState = NoAppBottomBar
        )
    }

    private fun setMainMenuState() {
        appState = appState.copy(
            drawerGesturesEnabled = true,
            isAppBackgroundBlur = true,
            appTopBarState = AppTopBar(
                titleRes = R.string.app_top_bar_title_menu,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.Menu,
                    onClick = {
                        if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close()
                        else scaffoldState.drawerState.open()
                    }
                )
            ),
            appBottomBarState = NoAppBottomBar
        )
    }

    private fun setMapCalloutsState() {
        appState = appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppTopBar(
                titleRes = R.string.app_top_bar_title_map_callouts,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            )
        )
    }

    private fun setWeaponCharacteristicsState() {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.Ranks.navId, true)
                .build()
        )
        appState = appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppTopBar(
                titleRes = R.string.app_top_bar_title_weapon_characteristics,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppBottomBar(
                listOf(
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_competitive,
                        icon = R.drawable.icon_competitive,
                        route = TargetNavigation.RanksCompetitive().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksCompetitive(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_wingman,
                        icon = R.drawable.icon_wingman,
                        route = TargetNavigation.RanksWingman().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksWingman(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_danger_zone,
                        icon = R.drawable.icon_danger_zone,
                        route = TargetNavigation.RanksDangerZone().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksDangerZone(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_profile_rank,
                        icon = R.drawable.icon_profile_rank,
                        route = TargetNavigation.RanksProfileRank().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksProfileRank(navParams)
                            )
                        }
                    )
                )
            )
        )
    }

    private fun setGrenadesPracticeState() {
        appState = appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppTopBar(
                titleRes = R.string.app_top_bar_title_grenades_practice,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = NoAppBottomBar
        )
    }

    private fun setRanksState() {
        val navParams = NavigationParams(
            navOptions = NavOptions.Builder()
                .setPopUpTo(TargetNavigation.Ranks.navId, true)
                .build()
        )

        appState = appState.copy(
            drawerGesturesEnabled = false,
            appTopBarState = AppTopBar(
                titleRes = R.string.app_top_bar_title_ranks,
                appTopBarNavItem = AppState.AppTopBarNavItem(
                    icon = Icons.Filled.KeyboardArrowLeft,
                    onClick = { navigateTo(TargetNavigation.Back) }
                )
            ),
            appBottomBarState = AppBottomBar(
                listOf(
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_competitive,
                        icon = R.drawable.icon_competitive,
                        route = TargetNavigation.RanksCompetitive().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksCompetitive(
                                    navParams
                                )
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_wingman,
                        icon = R.drawable.icon_wingman,
                        route = TargetNavigation.RanksWingman().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksWingman(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_danger_zone,
                        icon = R.drawable.icon_danger_zone,
                        route = TargetNavigation.RanksDangerZone().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksDangerZone(navParams)
                            )
                        }
                    ),
                    AppState.AppBottomBarItem(
                        label = R.string.app_bottom_bar_ranks_profile_rank,
                        icon = R.drawable.icon_profile_rank,
                        route = TargetNavigation.RanksProfileRank().path,
                        onClick = {
                            navigateTo(
                                TargetNavigation.RanksProfileRank(navParams)
                            )
                        }
                    )
                )
            )
        )
    }

    private fun setRanksCompetitiveState() {
        setAppTopBarTitle(R.string.app_top_bar_title_ranks_competitive)
    }

    private fun setRanksWingmanState() {
        setAppTopBarTitle(R.string.app_top_bar_title_ranks_wingman)
    }

    private fun setRanksDangerZoneState() {
        setAppTopBarTitle(R.string.app_top_bar_title_ranks_danger_zone)
    }

    private fun setRanksProfileRankState() {
        setAppTopBarTitle(R.string.app_top_bar_title_ranks_profile_rank)
    }

    private fun subscribeToNavEvent() = cs.launch {
        navEventCollector.collect { event ->
            scaffoldState.drawerState.close()
            when (event) {
                is TargetNavigation.Back -> Unit
                is TargetNavigation.SplashScreen -> setSplashScreenState()
                is TargetNavigation.Preview -> setPreviewState()
                is TargetNavigation.MainMenu -> setMainMenuState()
                is TargetNavigation.MapCallouts -> setMapCalloutsState()
                is TargetNavigation.WeaponCharacteristics -> setWeaponCharacteristicsState()
                is TargetNavigation.GrenadesPractice -> setGrenadesPracticeState()
                is TargetNavigation.Ranks -> setRanksState()
                is TargetNavigation.RanksCompetitive -> setRanksCompetitiveState()
                is TargetNavigation.RanksDangerZone -> setRanksDangerZoneState()
                is TargetNavigation.RanksProfileRank -> setRanksProfileRankState()
                is TargetNavigation.RanksWingman -> setRanksWingmanState()
            }
        }
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->

        }
    }

    private fun navigateTo(targetNavigation: TargetNavigation) = cs.launch {
        navEventEmitter.emit(targetNavigation)
    }

    private fun setAppTopBarTitle(@StringRes titleRes: Int) {
        (appState.appTopBarState as? AppTopBar)?.let { appTopBar ->
            appState = appState.copy(appTopBarState = appTopBar.copy(titleRes = titleRes))
        } ?: throw IllegalStateException("Current screen no has AppTopBar")
    }
}



