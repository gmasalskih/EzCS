package ru.gmasalskikh.ezcs.screens.preview

import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

data class PreviewViewState(
    val items: List<String> = listOf("weapon characteristics", "compare weapons", "maps", "grenades practice", "ranks"),
    val pagerState: PagerState? = null
)