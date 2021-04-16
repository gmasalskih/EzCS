package ru.gmasalskikh.ezcs.screens.grenade_practice_type_of_grenade

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class GrenadePracticeTypeOfGrenadeViewState(
    val mapHolders: List<MapHolder> = listOf(),
    val currentGrenadeType: GrenadeType = GrenadeType.SMOKE,
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState