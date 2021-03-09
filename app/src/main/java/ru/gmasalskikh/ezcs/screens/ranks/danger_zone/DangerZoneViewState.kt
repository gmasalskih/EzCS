package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import androidx.compose.runtime.Immutable
import ru.gmasalskikh.ezcs.data.app_entity.DangerZone
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

@Immutable
data class DangerZoneViewState(
    val items: List<DangerZone> = listOf(),
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState