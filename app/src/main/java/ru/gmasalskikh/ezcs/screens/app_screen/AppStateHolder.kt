package ru.gmasalskikh.ezcs.screens.app_screen

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import ru.gmasalskikh.ezcs.navigation.Navigator
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

interface AppStateHolder {
    val appViewState: AppViewState
    val appViewEventEmitter: FlowCollector<AppViewEvent>
    val appViewEventCollector: SharedFlow<AppViewEvent>
    fun onViewCreate()
    fun onViewDestroy()
    fun setScaffoldState(scaffoldState: ScaffoldState)

    sealed class AppViewEvent {
        data class NavigateTo(
            val targetNavigation: TargetNavigation
        ) : AppViewEvent()
        object GrenadesPracticeSetState : AppViewEvent()
        object MainMenuSetState : AppViewEvent()
        object MapCalloutsSetState : AppViewEvent()
        object PreviewSetState : AppViewEvent()
        object RanksCompetitiveSetState : AppViewEvent()
        object RanksDangerZoneSetState : AppViewEvent()
        object RanksProfileRankSetState : AppViewEvent()
        object RanksSetState : AppViewEvent()
        object RanksWingmanSetState : AppViewEvent()
        object SplashScreenSetState : AppViewEvent()
        object WeaponCharacteristicsHeavySetState : AppViewEvent()
        object WeaponCharacteristicsPistolSetState : AppViewEvent()
        object WeaponCharacteristicsRifleSetState : AppViewEvent()
        object WeaponCharacteristicsSMGSetState : AppViewEvent()
        object WeaponCharacteristicsSetState : AppViewEvent()
    }
}