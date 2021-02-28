package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.*
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.screens.app_screen.app_state_strategies.*
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolder.AppViewEvent.*

@Suppress("ObjectPropertyName")
class AppStateHolderImpl(
    private val cs: CustomCoroutineScope,
) : AppStateHolder {

    private val _appViewEvent = MutableSharedFlow<AppViewEvent>()
    override val appViewEventCollector: SharedFlow<AppViewEvent>
        get() = _appViewEvent
    override val appViewEventEmitter: FlowCollector<AppViewEvent>
        get() = _appViewEvent

    private lateinit var scaffoldState: ScaffoldState

    override var appViewState: AppViewState by mutableStateOf(AppViewState())
        private set

    override fun onViewCreate() {
        cs.onStart()
        subscribeToViewEvent()
    }

    override fun onViewDestroy() {
        cs.onStop()
    }

    override fun setScaffoldState(scaffoldState: ScaffoldState) {
        this.scaffoldState = scaffoldState
    }

    private fun subscribeToViewEvent() = cs.launch {
        _appViewEvent.collect { event ->
            val appStateStrategy: AppStateStrategy? = when (event) {
                is GrenadesPracticeSetState -> {
                    GrenadesPracticeStrategy(appViewState, appViewEventEmitter, this)
                }
                is MainMenuSetState -> {
                    MainMenuStrategy(appViewState, scaffoldState)
                }
                is MapCalloutsSetState -> {
                    MapCalloutsStrategy(appViewState, appViewEventEmitter, this)
                }
                is NavigateTo -> {
                    null
                }
                is PreviewSetState -> {
                    PreviewStrategy(appViewState)
                }
                is RanksCompetitiveSetState -> {
                    RanksCompetitiveStrategy(appViewState)
                }
                is RanksDangerZoneSetState -> {
                    RanksDangerZoneStrategy(appViewState)
                }
                is RanksProfileRankSetState -> {
                    RanksProfileRankStrategy(appViewState)
                }
                is RanksSetState -> {
                    RanksStrategy(appViewState, appViewEventEmitter, cs)
                }
                is RanksWingmanSetState -> {
                    RanksWingmanStrategy(appViewState)
                }
                is SplashScreenSetState -> {
                    SplashScreenStrategy(appViewState)
                }
                is WeaponCharacteristicsHeavySetState -> {
                    WeaponCharacteristicsHeavyStrategy(appViewState)
                }
                is WeaponCharacteristicsPistolSetState -> {
                    WeaponCharacteristicsPistolStrategy(appViewState)
                }
                is WeaponCharacteristicsSMGSetState -> {
                    WeaponCharacteristicsSMGStrategy(appViewState)
                }
                is WeaponCharacteristicsSetState -> {
                    WeaponCharacteristicsStrategy(appViewState, appViewEventEmitter, this)
                }
                is WeaponCharacteristicsRifleSetState -> {
                    WeaponCharacteristicsRifleStrategy(appViewState)
                }
            }
            appStateStrategy?.let { strategy -> appViewState = strategy.applyStrategy() }
        }
    }
}



