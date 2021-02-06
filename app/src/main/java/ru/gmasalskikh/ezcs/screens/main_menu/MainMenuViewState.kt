package ru.gmasalskikh.ezcs.screens.main_menu

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
){
    data class MainMenuItem(
        val mainMenuItemType: MainMenuItemType
    )
    enum class MainMenuItemType {
        MAP_CALLOUTS,
        GRENADES_PRACTICE,
        WEAPON_CHARACTERISTICS,
        RANKS
    }
}