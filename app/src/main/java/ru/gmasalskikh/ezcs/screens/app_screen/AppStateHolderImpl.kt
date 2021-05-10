package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath.*
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies.*
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.GrenadePracticeDetailsViewModel
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.PlacesOnMapsViewModel
import ru.gmasalskikh.ezcs.screens.map_callouts_details.MapCalloutsDetailsViewModel

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
        appEventCollector.mapNotNull { event ->
            (event as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent
        }.collect { navEvent ->
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
                    MapCalloutsStrategy(appViewState, _appViewEvent)
                }
                MAP_CALLOUTS_DETAILS -> {
                    navEvent.bundle?.getString(
                        MapCalloutsDetailsViewModel.MAP_CALLOUTS_MAP_NAME
                    )?.let { topAppBarTitle ->
                        MapCalloutsDetailsStrategy(appViewState, topAppBarTitle)
                    }
                }
                WEAPON_CHARACTERISTICS -> {
                    WeaponCharacteristicsStrategy(appViewState, _appViewEvent)
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
                GRENADE_PRACTICE, GRENADE_PRACTICE_SMOKE,
                GRENADE_PRACTICE_MOLOTOV, GRENADE_PRACTICE_FLASH -> {
                    if (navEvent.path != GRENADE_PRACTICE) {
                        appViewState = GrenadesPracticeStrategy(
                            appViewState = appViewState,
                            appViewEventEmitter = _appViewEvent
                        ).applyStrategy()
                    }
                    when (navEvent.path) {
                        GRENADE_PRACTICE_SMOKE -> {
                            GrenadesPracticeStrategySmoke(appViewState)
                        }
                        GRENADE_PRACTICE_MOLOTOV -> {
                            GrenadesPracticeStrategyMolotov(appViewState)
                        }
                        GRENADE_PRACTICE_FLASH -> {
                            GrenadesPracticeStrategyFlash(appViewState)
                        }
                        else -> null
                    }
                }
                GRENADE_PRACTICE_TICKRATE, GRENADE_PRACTICE_TICKRATE_64,
                GRENADE_PRACTICE_TICKRATE_128 -> {
                    val mapName =
                        navEvent.bundle?.getString(PlacesOnMapsViewModel.GRENADE_PRACTICE_MAP_NAME)
                    val grenadeTypeName =
                        navEvent.bundle?.getString(PlacesOnMapsViewModel.GRENADE_PRACTICE_GRENADE_TYPE)
                    if(navEvent.path != GRENADE_PRACTICE_TICKRATE) {
                        appViewState = GrenadesPracticeStrategyTickrate(
                            appViewState = appViewState,
                            appViewEventEmitter = _appViewEvent,
                            mapName = mapName,
                            grenadeTypeName = grenadeTypeName
                        ).applyStrategy()
                    }
                    when(navEvent.path) {
                        GRENADE_PRACTICE_TICKRATE_64 ->
                            GrenadesPracticeStrategyTickrate64(
                                appViewState = appViewState,
                                mapName = mapName,
                                grenadeTypeName = grenadeTypeName)
                        GRENADE_PRACTICE_TICKRATE_128 ->
                            GrenadesPracticeStrategyTickrate128(
                                appViewState = appViewState,
                                mapName = mapName,
                                grenadeTypeName = grenadeTypeName)
                        else -> null
                    }
                }
                GRENADE_PRACTICE_DETAILS -> {
                    navEvent.bundle?.getString(
                        GrenadePracticeDetailsViewModel.GRENADE_PRACTICE_DETAILS_MAPPOINT_NAME
                    ).let { mapPointName ->
                        GrenadesPracticeStrategyDetails(appViewState, _appViewEvent, mapPointName)
                    }
                }
                RANKS -> {
                    RanksStrategy(appViewState, _appViewEvent)
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



