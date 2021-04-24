package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details

import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WeaponCharacteristicsDetailsViewState(
    override var currentSideEffect: SideEffect = SideEffect.Loading,
    val weapon: Weapon? = null
) : ViewState