package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class MainMenuViewState(
    val menuListItem: List<MainMenuItem> = listOf(
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.MAP_CALLOUTS,
            menuItemNameRes = R.string.main_menu_item_name_map_callouts,
            menuItemBackgroundImageRes = R.drawable.main_menu_background_map_callouts
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.WEAPON_CHARACTERISTICS,
            menuItemNameRes = R.string.main_menu_item_name_weapon_characteristics,
            menuItemBackgroundImageRes = R.drawable.main_menu_background_weapon_characteristics
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.GRENADES_PRACTICE,
            menuItemNameRes = R.string.main_menu_item_name_grenades_practice,
            menuItemBackgroundImageRes = R.drawable.main_menu_background_grenades_practice
        ),
        MainMenuItem(
            mainMenuItemType = MainMenuItemType.RANKS,
            menuItemNameRes = R.string.main_menu_item_name_ranks,
            menuItemBackgroundImageRes = R.drawable.main_menu_background_ranks
        )
    ),
    override var currentSideEffect: SideEffect = SideEffect.Data
) : ViewState {
    data class MainMenuItem(
        val mainMenuItemType: MainMenuItemType,

        @StringRes
        val menuItemNameRes: Int,

        @DrawableRes
        val menuItemBackgroundImageRes: Int
    )

    enum class MainMenuItemType {
        MAP_CALLOUTS,
        GRENADES_PRACTICE,
        WEAPON_CHARACTERISTICS,
        RANKS
    }
}