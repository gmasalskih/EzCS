package ru.gmasalskikh.ezcs.screens.main_menu

import ru.gmasalskikh.ezcs.data.items.MainMenuItem
import ru.gmasalskikh.ezcs.data.types.MainMenuItemType

data class MainMenuViewState(
    val menuListItem: List<MainMenuItem> = listOf(
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.MAP_CALLOUTS
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.WEAPON_CHARACTERISTICS
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.GRENADES_PRACTICE
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.RANKS
        )
    )
)