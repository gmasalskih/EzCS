package ru.gmasalskikh.ezcs.screens.ranks.danger_zone

import androidx.compose.runtime.Immutable
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

@Immutable
data class DangerZoneViewState(
    val name: String = "DangerZone",
    override val currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState