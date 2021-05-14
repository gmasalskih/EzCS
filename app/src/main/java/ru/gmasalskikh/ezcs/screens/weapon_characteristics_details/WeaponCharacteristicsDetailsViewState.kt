package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details

import ru.gmasalskikh.ezcs.data.view_entity.WeaponItem
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WeaponCharacteristicsDetailsViewState(
    override var currentSideEffect: SideEffect = SideEffect.Loading,
    val weaponItem: WeaponItem? = null
) : ViewState