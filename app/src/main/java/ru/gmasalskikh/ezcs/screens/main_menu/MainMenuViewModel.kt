package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import ru.gmasalskikh.ezcs.data.types.MainMenuItemType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation

class MainMenuViewModel : ViewModel() {
    var viewState: MainMenuViewState by mutableStateOf(MainMenuViewState())
        private set

    fun navigateTo(navController: NavController, mainMenuItemType: MainMenuItemType) {
        when (mainMenuItemType) {
            MainMenuItemType.MAP_CALLOUTS -> {
            }
            MainMenuItemType.GRENADES_PRACTICE -> {
            }
            MainMenuItemType.WEAPON_CHARACTERISTICS -> {
            }
            MainMenuItemType.RANKS -> navController.navigate(TargetNavigation.RANKS.name)

        }
    }
}