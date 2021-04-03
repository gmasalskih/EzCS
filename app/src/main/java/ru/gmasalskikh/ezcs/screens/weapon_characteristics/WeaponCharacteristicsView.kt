package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.widgets.WeaponListItem

class WeaponCharacteristicsView(
    vm: WeaponCharacteristicsViewModel,
) : BaseView<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent, WeaponCharacteristicsViewModel>(
    vm
) {

    @Composable
    override fun SetContent(viewState: WeaponCharacteristicsViewState) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val weapons = viewState.weapons.filter { viewState.currentWeaponType == it.weaponType }
            items(weapons) { weapon ->
                WeaponListItem(
                    weapon = weapon,
                    isSelected = viewState.selectedWeapon.contains(weapon)
                )
            }
        }
    }
}