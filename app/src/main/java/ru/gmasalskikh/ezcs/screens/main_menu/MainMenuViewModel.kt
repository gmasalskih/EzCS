package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.screens.SideEffect

class MainMenuViewModel(
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : BaseViewModel<MainMenuViewState>(
    defaultViewState = MainMenuViewState(),
    initViewStateType = ViewStateType.Data
) {
    fun navigateTo(
        mainMenuItemType: MainMenuViewState.MainMenuItemType
    ) {
        when (mainMenuItemType) {
            MainMenuViewState.MainMenuItemType.MAP_CALLOUTS -> {
            }
            MainMenuViewState.MainMenuItemType.GRENADES_PRACTICE -> {
            }
            MainMenuViewState.MainMenuItemType.WEAPON_CHARACTERISTICS -> {
            }
            MainMenuViewState.MainMenuItemType.RANKS -> viewModelScope.launch {
                navEventEmitter.emit(TargetNavigation.Ranks)
            }
        }

    }

    override val container: Container<MainMenuViewState, SideEffect>
        get() = TODO("Not yet implemented")
}