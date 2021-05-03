package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.data.view_entity.WeaponItem

@Composable
fun WeaponCharacteristicsDetails(weaponItem: WeaponItem) {
    Column(
        modifier = Modifier
            .scrollable(state = ScrollState(0), orientation = Orientation.Vertical)
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        WeaponAppearanceImage(
            modifier = Modifier.weight(1f),
            deferredBitmap = weaponItem.logoDeferred,
            teamTypes = weaponItem.teamTypes
        )
        WeaponRecoilDiagram(
            modifier = Modifier.weight(1f),
            sprayDeferred = weaponItem.sprayDeferred,
            recoilDeferred = weaponItem.recoilDeferred
        )
        WeaponDetails(
            modifier = Modifier.weight(1f),
            weaponDetails = weaponItem.listDetails
        )
    }
}