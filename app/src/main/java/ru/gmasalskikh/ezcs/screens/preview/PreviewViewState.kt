package ru.gmasalskikh.ezcs.screens.preview

import ru.gmasalskikh.ezcs.data.items.PreviewItem
import ru.gmasalskikh.ezcs.data.types.PreviewItemType
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

data class PreviewViewState(
    val items: List<PreviewItem> = listOf(
        PreviewItem(type = PreviewItemType.WEAPON_CHARACTERISTICS),
        PreviewItem(type = PreviewItemType.COMPARE_WEAPONS),
        PreviewItem(type = PreviewItemType.GRENADES_PRACTICE),
        PreviewItem(type = PreviewItemType.MAP_CALLOUTS),
        PreviewItem(type = PreviewItemType.RANKS)
    ),
    val pagerState: PagerState? = null
)