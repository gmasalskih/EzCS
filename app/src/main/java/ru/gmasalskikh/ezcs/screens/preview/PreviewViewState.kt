package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.ViewState
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

data class PreviewViewState(
    val items: List<PreviewItem> = listOf(
        PreviewItem(
            type = PreviewItemType.MAP_CALLOUTS,
            descriptionRes = R.string.map_callouts,
            imageRes = R.drawable.preview_map_callouts,
        ),
        PreviewItem(
            type = PreviewItemType.COMPARE_WEAPONS,
            descriptionRes = R.string.compare_weapons,
            imageRes = R.drawable.preview_compare_weapons,
        ),
        PreviewItem(
            type = PreviewItemType.GRENADES_PRACTICE,
            descriptionRes = R.string.grenades_practice,
            imageRes = R.drawable.preview_grenades_practice,
        ),
        PreviewItem(
            type = PreviewItemType.WEAPON_CHARACTERISTICS,
            descriptionRes = R.string.weapon_characteristics,
            imageRes = R.drawable.preview_weapon_characteristics,
        ),
        PreviewItem(
            type = PreviewItemType.RANKS,
            descriptionRes = R.string.ranks,
            imageRes = R.drawable.preview_rangs,
        )
    ),
    val pagerState: PagerState? = null
) : ViewState {
    data class PreviewItem(
        val type: PreviewItemType,

        @StringRes
        val descriptionRes: Int,

        @DrawableRes
        val imageRes: Int
    )

    enum class PreviewItemType {
        MAP_CALLOUTS,
        COMPARE_WEAPONS,
        GRENADES_PRACTICE,
        WEAPON_CHARACTERISTICS,
        RANKS
    }
}