package ru.gmasalskikh.ezcs.providers.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R

import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType
import ru.gmasalskikh.ezcs.screens.main_menu.MainMenuViewState
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewState

class ResourceMapperImpl : ResourceMapper {

    private fun getMainMenuItemPairRes(mainMenuItemType: MainMenuViewState.MainMenuItemType): Pair<Int, Int> =
        when (mainMenuItemType) {
            MainMenuViewState.MainMenuItemType.MAP_CALLOUTS -> {
                R.string.preview_topic_map_callouts to R.drawable.main_menu_background_map_callouts
            }
            MainMenuViewState.MainMenuItemType.GRENADES_PRACTICE -> {
                R.string.preview_topic_grenades_practice to R.drawable.main_menu_background_grenades_practice
            }
            MainMenuViewState.MainMenuItemType.WEAPON_CHARACTERISTICS -> {
                R.string.preview_topic_weapon_characteristics to R.drawable.main_menu_background_weapon_characteristics
            }
            MainMenuViewState.MainMenuItemType.RANKS -> {
                R.string.preview_topic_ranks to R.drawable.main_menu_background_ranks
            }
        }

    private fun getRankBottomAppBarItemPairRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType): Pair<Int, Int> =
        when (ranksBottomAppBarItemType) {
            RanksBottomAppBarItemType.COMPETITIVE -> {
                R.string.competitive to R.drawable.icon_competitive
            }
            RanksBottomAppBarItemType.WINGMAN -> {
                R.string.wingman to R.drawable.icon_wingman
            }
            RanksBottomAppBarItemType.DANGER_ZONE -> {
                R.string.danger_zone to R.drawable.icon_danger_zone
            }
            RanksBottomAppBarItemType.PROFILE_RANK -> {
                R.string.profile_rank to R.drawable.icon_profile_rank
            }
        }

    @StringRes
    override fun getStringRes(mainMenuItemType: MainMenuViewState.MainMenuItemType): Int =
        getMainMenuItemPairRes(mainMenuItemType = mainMenuItemType).first

    @DrawableRes
    override fun getDrawableRes(mainMenuItemType: MainMenuViewState.MainMenuItemType): Int =
        getMainMenuItemPairRes(mainMenuItemType = mainMenuItemType).second

    @DrawableRes
    override fun getDrawableRes(itemType: PreviewViewState.PreviewItemType): Int = when (itemType) {
        PreviewViewState.PreviewItemType.MAP_CALLOUTS -> R.drawable.preview_map_callouts
        PreviewViewState.PreviewItemType.COMPARE_WEAPONS -> R.drawable.preview_compare_weapons
        PreviewViewState.PreviewItemType.GRENADES_PRACTICE -> R.drawable.preview_grenades_practice
        PreviewViewState.PreviewItemType.WEAPON_CHARACTERISTICS -> R.drawable.preview_weapon_characteristics
        PreviewViewState.PreviewItemType.RANKS -> R.drawable.preview_rangs
    }

    @StringRes
    override fun getStringRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType): Int =
        getRankBottomAppBarItemPairRes(ranksBottomAppBarItemType).first

    @DrawableRes
    override fun getDrawableRes(ranksBottomAppBarItemType: RanksBottomAppBarItemType): Int =
        getRankBottomAppBarItemPairRes(ranksBottomAppBarItemType).second
}