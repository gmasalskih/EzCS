package ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class GrenadePracticeTypeOfGrenadeViewEvent : ViewEvent {
    data class NavigateTo(
        val mapHolder: MapHolder,
        val grenadeTypeName: String
    ) : GrenadePracticeTypeOfGrenadeViewEvent()
}