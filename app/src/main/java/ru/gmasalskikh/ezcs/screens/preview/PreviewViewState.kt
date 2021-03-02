package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState
//import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

data class PreviewViewState(
    val items: List<PreviewItem> = listOf(
        PreviewItem(
            type = PreviewItemType.GRENADES_PRACTICE,
            topicRes = R.string.preview_topic_grenades_practice,
            imageRes = R.drawable.preview_grenades_practice,
        ),
        PreviewItem(
            type = PreviewItemType.MAP_CALLOUTS,
            topicRes = R.string.preview_topic_map_callouts,
            imageRes = R.drawable.preview_map_callouts,
        ),
        PreviewItem(
            type = PreviewItemType.WEAPON_CHARACTERISTICS,
            topicRes = R.string.preview_topic_weapon_characteristics,
            imageRes = R.drawable.preview_weapon_characteristics,
        ),
        PreviewItem(
            type = PreviewItemType.COMPARE_WEAPONS,
            topicRes = R.string.preview_topic_compare_weapons,
            imageRes = R.drawable.preview_compare_weapons,
        ),
        PreviewItem(
            type = PreviewItemType.RANKS,
            topicRes = R.string.preview_topic_ranks,
            imageRes = R.drawable.preview_rangs,
        )
    ),
//    val pagerState: PagerState? = null,
    override val currentSideEffect: SideEffect = SideEffect.Data
) : ViewState {

    data class PreviewItem(
        val type: PreviewItemType,

        @StringRes
        val topicRes: Int,

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