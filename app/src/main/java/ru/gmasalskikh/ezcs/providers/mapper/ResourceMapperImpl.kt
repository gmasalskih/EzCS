package ru.gmasalskikh.ezcs.providers.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.types.MainMenuItemType
import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType
import ru.gmasalskikh.ezcs.screens.preview.PreviewViewState

class ResourceMapperImpl : ResourceMapper {

    private fun getMainMenuItemPairRes(mainMenuItemType: MainMenuItemType): Pair<Int, Int> =
        when (mainMenuItemType) {
            MainMenuItemType.MAP_CALLOUTS -> {
                R.string.map_callouts to R.drawable.main_menu_map_callouts
            }
            MainMenuItemType.GRENADES_PRACTICE -> {
                R.string.grenades_practice to R.drawable.main_menu_grenades_practice
            }
            MainMenuItemType.WEAPON_CHARACTERISTICS -> {
                R.string.weapon_characteristics to R.drawable.main_menu_weapon_characteristics
            }
            MainMenuItemType.RANKS -> {
                R.string.ranks to R.drawable.main_menu_ranks
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
    override fun getStringRes(mainMenuItemType: MainMenuItemType): Int =
        getMainMenuItemPairRes(mainMenuItemType = mainMenuItemType).first

    @DrawableRes
    override fun getDrawableRes(mainMenuItemType: MainMenuItemType): Int =
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