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

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>,
    private val navEventCollector: Flow<Navigator.NavEvent>
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

    private fun subscribeToNavEvent() = cs.launch {
        navEventCollector.collect { event ->
            scaffoldState.drawerState.close()
            val appStateStrategy: AppStateStrategy? = when (event.path) {
                SPLASH_SCREEN -> SplashScreenStrategy(appState)
                PREVIEW -> PreviewStrategy(appState)
                MAIN_MENU -> MainMenuStrategy(appState, scaffoldState)
                MAP_CALLOUTS -> MapCalloutsStrategy(appState, navEventEmitter, cs)
                WEAPON_CHARACTERISTICS -> {
                    WeaponCharacteristicsStrategy(appState, navEventEmitter, cs)
                }
                WEAPON_CHARACTERISTICS_PISTOL -> WeaponCharacteristicsPistolStrategy(appState)
                WEAPON_CHARACTERISTICS_HEAVY -> WeaponCharacteristicsHeavyStrategy(appState)
                WEAPON_CHARACTERISTICS_SMG -> WeaponCharacteristicsSMGStrategy(appState)
                WEAPON_CHARACTERISTICS_RIFLE -> WeaponCharacteristicsRifleStrategy(appState)
                GRENADES_PRACTICE -> GrenadesPracticeStrategy(appState, navEventEmitter, cs)
                RANKS -> RanksStrategy(appState, navEventEmitter, cs)
                RANKS_COMPETITIVE -> RanksCompetitiveStrategy(appState)
                RANKS_WINGMAN -> RanksWingmanStrategy(appState)
                RANKS_DANGER_ZONE -> RanksDangerZoneStrategy(appState)
                RANKS_PROFILE_RANK -> RanksProfileRankStrategy(appState)
                BACK -> null
            }
            appStateStrategy?.let { strategy -> appState = strategy.applyStrategy() }
        }
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->

        }
    }

    private fun navigateTo(targetNavigation: TargetNavigation) = cs.launch {
        navEventEmitter.emit(targetNavigation)
    }
}



