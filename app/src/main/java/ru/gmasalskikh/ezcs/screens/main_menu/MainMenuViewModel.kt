package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
//import ru.gmasalskikh.ezcs.data.types.MainMenuItemType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

class MainMenuViewModel : ViewModel() {
    var viewState: MainMenuViewState by mutableStateOf(MainMenuViewState())
        private set

    fun navigateTo(navController: NavController, mainMenuItemType: MainMenuViewState.MainMenuItemType) {
        when (mainMenuItemType) {
            MainMenuViewState.MainMenuItemType.MAP_CALLOUTS -> {
            }
            MainMenuViewState.MainMenuItemType.GRENADES_PRACTICE -> {
            }
            MainMenuViewState.MainMenuItemType.WEAPON_CHARACTERISTICS -> {
            }
            MainMenuViewState.MainMenuItemType.RANKS -> navController.navigate(TargetNavigation.Ranks.path)

        }
    }
}