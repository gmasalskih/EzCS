package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.data.types.ViewStateType

class MainMenuViewModel : BaseViewModel<MainMenuViewState>(
    defaultViewState = MainMenuViewState(),
    initViewStateType = ViewStateType.Data
) {

    fun navigateTo(
        navController: NavController,
        mainMenuItemType: MainMenuViewState.MainMenuItemType
    ) {
        when (mainMenuItemType) {
            MainMenuViewState.MainMenuItemType.MAP_CALLOUTS -> {
            }
            MainMenuViewState.MainMenuItemType.GRENADES_PRACTICE -> {
            }
            MainMenuViewState.MainMenuItemType.WEAPON_CHARACTERISTICS -> {
            }
            MainMenuViewState.MainMenuItemType.RANKS -> navController.navigate(TargetNavigation.Ranks().path) {
                launchSingleTop = true
            }

        }
    }
}