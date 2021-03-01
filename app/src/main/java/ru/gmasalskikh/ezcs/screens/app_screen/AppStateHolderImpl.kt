package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies.*
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
    private val appEventCollector: SharedFlow<AppController.AppEvent>,
    private val appEventEmitter: FlowCollector<AppController.AppEvent>
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<AppViewEvent>()

    private lateinit var scaffoldState: ScaffoldState

    override var appViewState: AppViewState by mutableStateOf(AppViewState())
        private set

    override fun onViewCreate() {
        cs.onStart()
        cs.launch { subscribeToAppEvent() }
        cs.launch { subscribeToAppViewEvent() }
    }

    override fun onViewDestroy() {
        cs.onStop()
    }

    override fun setScaffoldState(scaffoldState: ScaffoldState) {
        this.scaffoldState = scaffoldState
    }

    private suspend fun subscribeToAppViewEvent() {
        _appViewEvent.collect { event ->
            when (event) {
                is AppViewEvent.NavigateTo -> appEventEmitter.emit(
                    AppController.AppEvent.NavigateTo(
                        event.targetNavigation
                    )
                )
            }
        }
    }

    private suspend fun subscribeToAppEvent() {
        appEventCollector.map { event ->
            (event as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent
        }.filterNotNull()
            .collect { navEvent ->
                val appStateStrategy: AppStateStrategy? = when (navEvent.path) {
                    SPLASH_SCREEN -> {
                        SplashScreenStrategy(appViewState)
                    }
                    PREVIEW -> {
                        PreviewStrategy(appViewState)
                    }
                    MAIN_MENU -> {
                        MainMenuStrategy(appViewState, scaffoldState)
                    }
                    MAP_CALLOUTS -> {
                        MapCalloutsStrategy(appViewState, _appViewEvent, cs)
                    }
                    WEAPON_CHARACTERISTICS -> {
                        WeaponCharacteristicsStrategy(appViewState, _appViewEvent, cs)
                    }
                    WEAPON_CHARACTERISTICS_PISTOL -> {
                        WeaponCharacteristicsPistolStrategy(appViewState)
                    }
                    WEAPON_CHARACTERISTICS_HEAVY -> {
                        WeaponCharacteristicsHeavyStrategy(appViewState)
                    }
                    WEAPON_CHARACTERISTICS_SMG -> {
                        WeaponCharacteristicsSMGStrategy(appViewState)
                    }
                    WEAPON_CHARACTERISTICS_RIFLE -> {
                        WeaponCharacteristicsRifleStrategy(appViewState)
                    }
                    GRENADES_PRACTICE -> {
                        GrenadesPracticeStrategy(appViewState, _appViewEvent, cs)
                    }
                    RANKS -> {
                        RanksStrategy(appViewState, _appViewEvent, cs)
                    }
                    RANKS_COMPETITIVE -> {
                        RanksCompetitiveStrategy(appViewState)
                    }
                    RANKS_WINGMAN -> {
                        RanksWingmanStrategy(appViewState)
                    }
                    RANKS_DANGER_ZONE -> {
                        RanksDangerZoneStrategy(appViewState)
                    }
                    RANKS_PROFILE_RANK -> {
                        RanksProfileRankStrategy(appViewState)
                    }
                    BACK -> null
                }
                appStateStrategy?.let { strategy -> appViewState = strategy.applyStrategy() }
            }
    }
}



