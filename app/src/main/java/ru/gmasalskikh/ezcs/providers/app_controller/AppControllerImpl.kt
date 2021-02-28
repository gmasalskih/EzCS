package ru.gmasalskikh.ezcs.providers.app_controller

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.AppViewEvent.*

class AppControllerImpl(
    private val cs: CustomCoroutineScope,
    private val navEventEmitter: FlowCollector<TargetNavigation>,
    private val navEventFlow: Flow<Navigator.NavEvent>,
    private val appViewEventEmitter: FlowCollector<AppStateHolder.AppViewEvent>,
    private val appViewEventCollector: SharedFlow<AppStateHolder.AppViewEvent>
) : AppController {

    private val _appEvent = MutableSharedFlow<AppController.AppEvent>()
    override val appEventEmitter: FlowCollector<AppController.AppEvent>
        get() = _appEvent

    init {
        cs.launch {
            subscribeToAppEvent()
        }
        cs.launch {
            subscribeToNavEvent()
        }
        cs.launch {
            subscribeToAppViewEvent()
        }
    }

    private suspend fun subscribeToAppViewEvent() {
        appViewEventCollector.collect { event ->
            when (event) {
                is NavigateTo -> {
                    navEventEmitter.emit(
                        event.targetNavigation
                    )
                }
                else -> Unit
            }
        }
    }

    private suspend fun subscribeToAppEvent() {
        _appEvent.collect { event ->
            when (event) {
                is AppController.AppEvent.NavigateTo -> {
                    navEventEmitter.emit(event.targetNavigation)
                }
            }
        }
    }

    private suspend fun subscribeToNavEvent() {
        navEventFlow.collect { event ->
            when (event.path) {
                TargetNavigationPath.SPLASH_SCREEN -> {
                    appViewEventEmitter.emit(SplashScreenSetState)
                }
                TargetNavigationPath.PREVIEW -> {
                    appViewEventEmitter.emit(PreviewSetState)
                }
                TargetNavigationPath.MAIN_MENU -> {
                    appViewEventEmitter.emit(MainMenuSetState)
                }
                TargetNavigationPath.MAP_CALLOUTS -> {
                    appViewEventEmitter.emit(MapCalloutsSetState)
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS -> {
                    appViewEventEmitter.emit(WeaponCharacteristicsSetState)
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS_PISTOL -> {
                    appViewEventEmitter.emit(WeaponCharacteristicsPistolSetState)
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS_HEAVY -> {
                    appViewEventEmitter.emit(WeaponCharacteristicsHeavySetState)
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS_SMG -> {
                    appViewEventEmitter.emit(WeaponCharacteristicsSMGSetState)
                }
                TargetNavigationPath.WEAPON_CHARACTERISTICS_RIFLE -> {
                    appViewEventEmitter.emit(WeaponCharacteristicsRifleSetState)
                }
                TargetNavigationPath.GRENADES_PRACTICE -> {
                    appViewEventEmitter.emit(GrenadesPracticeSetState)
                }
                TargetNavigationPath.RANKS -> {
                    appViewEventEmitter.emit(RanksSetState)
                }
                TargetNavigationPath.RANKS_COMPETITIVE -> {
                    appViewEventEmitter.emit(RanksCompetitiveSetState)
                }
                TargetNavigationPath.RANKS_WINGMAN -> {
                    appViewEventEmitter.emit(RanksWingmanSetState)
                }
                TargetNavigationPath.RANKS_DANGER_ZONE -> {
                    appViewEventEmitter.emit(RanksDangerZoneSetState)
                }
                TargetNavigationPath.RANKS_PROFILE_RANK -> {
                    appViewEventEmitter.emit(RanksProfileRankSetState)
                }
                TargetNavigationPath.BACK -> {
                }
            }
        }
    }
}