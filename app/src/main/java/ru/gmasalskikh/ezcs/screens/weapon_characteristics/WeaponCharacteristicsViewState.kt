package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.data.type.WeaponType
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WeaponCharacteristicsViewState(
    val weapons: List<Weapon> = listOf(),
    val selectedWeapon: MutableSet<Weapon> = mutableSetOf(),
    val currentWeaponType: WeaponType = WeaponType.PISTOL,
    override var currentSideEffect: SideEffect = SideEffect.Loading
) : ViewState