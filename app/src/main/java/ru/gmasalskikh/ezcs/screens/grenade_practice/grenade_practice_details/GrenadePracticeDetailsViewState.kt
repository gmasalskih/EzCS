package ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details

import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class GrenadePracticeDetailsViewState(
    override var currentSideEffect: SideEffect = SideEffect.Loading,
    val mapPoint: MapPoint?,
) : ViewState
