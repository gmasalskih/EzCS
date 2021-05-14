package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import ru.gmasalskikh.ezcs.data.app_entity.Weapon
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class WeaponCharacteristicsViewEvent : ViewEvent {
    data class NavigateTo(
        val weapon: Weapon
    ) : WeaponCharacteristicsViewEvent()
}