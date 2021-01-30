package ru.gmasalskikh.ezcs.screens.preview

import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.model.preview.PreviewItem
import ru.gmasalskikh.ezcs.data.model.preview.PreviewItemType
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

data class PreviewViewState(
    val items: List<PreviewItem> = listOf(
        PreviewItem(
            type = PreviewItemType.WEAPON_CHARACTERISTICS,
            drawableRes = R.drawable.preview_weapon_characteristics
        ),
        PreviewItem(
            type = PreviewItemType.COMPARE_WEAPONS,
            drawableRes = R.drawable.preview_compare_weapons
        ),
        PreviewItem(
            type = PreviewItemType.GRENADES_PRACTICE,
            drawableRes = R.drawable.preview_grenades_practice
        ),
        PreviewItem(
            type = PreviewItemType.MAP_CALLOUTS,
            drawableRes = R.drawable.preview_map_callouts
        ),
        PreviewItem(
            type = PreviewItemType.RANKS,
            drawableRes = R.drawable.preview_rangs
        )
    ),
    val pagerState: PagerState? = null
)