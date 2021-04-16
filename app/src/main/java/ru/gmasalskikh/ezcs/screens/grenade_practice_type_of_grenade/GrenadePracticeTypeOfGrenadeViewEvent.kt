package ru.gmasalskikh.ezcs.screens.grenade_practice_type_of_grenade

import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.screens.ViewEvent

sealed class GrenadePracticeTypeOfGrenadeViewEvent : ViewEvent {
    data class NavigateTo(
        val mapHolder: MapHolder
    ) : GrenadePracticeTypeOfGrenadeViewEvent()
}