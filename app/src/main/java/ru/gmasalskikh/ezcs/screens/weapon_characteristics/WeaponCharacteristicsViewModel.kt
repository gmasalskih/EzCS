package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class WeaponCharacteristicsViewModel
    : BaseViewModel<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent>() {

    override val container: Container<WeaponCharacteristicsViewState, SideEffect> = container(
        initialState = WeaponCharacteristicsViewState()
    )

    override suspend fun onViewEvent(viewEvent: WeaponCharacteristicsViewEvent) {
    }
}