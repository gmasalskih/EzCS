package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies.*
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*
import ru.gmasalskikh.ezcs.providers.app_controller.AppController

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
    private val appEventEmitter: FlowCollector<AppController.AppEvent>,
    private val navEventCollector: Flow<Navigator.NavEvent>
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<AppViewEvent>()
    override val appViewEventCollector: SharedFlow<AppViewEvent>
        get() = _appViewEvent.asSharedFlow()
    override val appViewEventEmitter: FlowCollector<AppViewEvent>
        get() = _appViewEvent

    private lateinit var scaffoldState: ScaffoldState

    override var appViewState: AppViewState by mutableStateOf(AppViewState())
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

    private fun subscribeToNavEvent() = cs.launch {
        navEventCollector.collect { event ->
            scaffoldState.drawerState.close()
            val appStateStrategy: AppStateStrategy? = when (event.path) {
                SPLASH_SCREEN -> SplashScreenStrategy(appViewState)
                PREVIEW -> PreviewStrategy(appViewState)
                MAIN_MENU -> MainMenuStrategy(appViewState, scaffoldState)
                MAP_CALLOUTS -> MapCalloutsStrategy(appViewState, appEventEmitter, cs)
                WEAPON_CHARACTERISTICS -> {
                    WeaponCharacteristicsStrategy(appViewState, appEventEmitter, cs)
                }
                WEAPON_CHARACTERISTICS_PISTOL -> WeaponCharacteristicsPistolStrategy(appViewState)
                WEAPON_CHARACTERISTICS_HEAVY -> WeaponCharacteristicsHeavyStrategy(appViewState)
                WEAPON_CHARACTERISTICS_SMG -> WeaponCharacteristicsSMGStrategy(appViewState)
                WEAPON_CHARACTERISTICS_RIFLE -> WeaponCharacteristicsRifleStrategy(appViewState)
                GRENADES_PRACTICE -> GrenadesPracticeStrategy(appViewState, appEventEmitter, cs)
                RANKS -> RanksStrategy(appViewState, appEventEmitter, cs)
                RANKS_COMPETITIVE -> RanksCompetitiveStrategy(appViewState)
                RANKS_WINGMAN -> RanksWingmanStrategy(appViewState)
                RANKS_DANGER_ZONE -> RanksDangerZoneStrategy(appViewState)
                RANKS_PROFILE_RANK -> RanksProfileRankStrategy(appViewState)
                BACK -> null
            }
            appStateStrategy?.let { strategy -> appViewState = strategy.applyStrategy() }
        }
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->

        }
    }
}



