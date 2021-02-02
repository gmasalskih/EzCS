package ru.gmasalskikh.ezcs.screens.ranks

import ru.gmasalskikh.ezcs.data.items.RankBottomAppBarItem
import ru.gmasalskikh.ezcs.data.types.RanksBottomAppBarItemType

data class RanksViewState(
    val selectedRank: RanksBottomAppBarItemType = RanksBottomAppBarItemType.COMPETITIVE,
    val listRankBottomAppBarItem: List<RankBottomAppBarItem> = listOf(
        RankBottomAppBarItem(
            type = RanksBottomAppBarItemType.COMPETITIVE
        ),
        RankBottomAppBarItem(
            type = RanksBottomAppBarItemType.WINGMAN
        ),
        RankBottomAppBarItem(
            type = RanksBottomAppBarItemType.DANGER_ZONE
        ),
        RankBottomAppBarItem(
            type = RanksBottomAppBarItemType.PROFILE_RANK
        )
    ),
    val list: List<String> = listOf(
        RanksBottomAppBarItemType.COMPETITIVE.name,
        RanksBottomAppBarItemType.WINGMAN.name,
        RanksBottomAppBarItemType.DANGER_ZONE.name,
        RanksBottomAppBarItemType.PROFILE_RANK.name
    )
)