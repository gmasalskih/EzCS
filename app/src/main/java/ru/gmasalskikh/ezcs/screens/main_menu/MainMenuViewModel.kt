package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class MainMenuViewModel(
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : BaseViewModel<MainMenuViewState, MainMenuViewEvent>() {

    override val container: Container<MainMenuViewState, SideEffect> = container(
        initialState = MainMenuViewState()
    )

    override suspend fun onViewEvent(viewEvent: MainMenuViewEvent) {
        when (viewEvent) {
            is MainMenuViewEvent.NavigateTo -> viewModelScope.launch {
                val targetNavigation = when (viewEvent.mainMenuItemType) {
                    MainMenuViewState.MainMenuItemType.MAP_CALLOUTS -> TODO()
                    MainMenuViewState.MainMenuItemType.GRENADES_PRACTICE -> TODO()
                    MainMenuViewState.MainMenuItemType.WEAPON_CHARACTERISTICS -> TODO()
                    MainMenuViewState.MainMenuItemType.RANKS -> TargetNavigation.Ranks
                }
                navEventEmitter.emit(targetNavigation)
            }
        }
    }
}