package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.ViewState

data class WeaponCharacteristicsViewState(
    val name: String = "Weapon characteristics",
    override var currentSideEffect: SideEffect = SideEffect.Data
) : ViewState