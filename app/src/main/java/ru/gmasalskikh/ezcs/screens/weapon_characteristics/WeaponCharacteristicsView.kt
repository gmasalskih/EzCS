package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView

class WeaponCharacteristicsView(
    vm: WeaponCharacteristicsViewModel
) : BaseView<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent, WeaponCharacteristicsViewModel>(
    vm
) {

    @Composable
    override fun SetContent(viewState: WeaponCharacteristicsViewState) {
        Text(text = viewState.name)
    }
}