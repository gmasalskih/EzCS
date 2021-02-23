package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class WeaponCharacteristicsViewEvent : ViewEvent {
    data class OnBottomBarItemNavigate(
        val targetNavigation: TargetNavigation
    ) : WeaponCharacteristicsViewEvent()
}