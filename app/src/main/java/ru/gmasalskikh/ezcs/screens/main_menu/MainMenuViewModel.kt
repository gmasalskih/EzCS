package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewState.MainMenuItemType.*

class MainMenuViewModel(
    private val appEventEmitter: FlowCollector<AppController.AppEvent>
) : BaseViewModel<MainMenuViewState, MainMenuViewEvent>() {

    override val container: Container<MainMenuViewState, SideEffect> = container(
        initialState = MainMenuViewState()
    )

    override suspend fun onViewEvent(viewEvent: MainMenuViewEvent) {
        when (viewEvent) {
            is MainMenuViewEvent.NavigateTo -> viewModelScope.launch {
                val targetNavigation = when (viewEvent.mainMenuItemType) {
                    MAP_CALLOUTS -> TargetNavigation.MapCallouts
                    GRENADES_PRACTICE -> TargetNavigation.GrenadesPractice
                    WEAPON_CHARACTERISTICS -> TargetNavigation.WeaponCharacteristics
                    RANKS -> TargetNavigation.Ranks
                }
                appEventEmitter.emit(
                    AppController.AppEvent.NavigateTo(
                        targetNavigation
                    )
                )
            }
        }
    }
}