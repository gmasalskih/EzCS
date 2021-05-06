package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.data.view_entity.WeaponItem

@Composable
fun WeaponCharacteristicsDetails(weaponItem: WeaponItem) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(state = ScrollState(0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            WeaponAppearanceImage(
                modifier = Modifier.height(200.dp),
                deferredBitmap = weaponItem.logoDeferred,
                teamTypes = weaponItem.teamTypes
            )
            WeaponRecoilDiagram(
                modifier = Modifier.wrapContentSize(),
                sprayDeferred = weaponItem.sprayDeferred,
                recoilDeferred = weaponItem.recoilDeferred
            )
            WeaponDetails(
                modifier = Modifier.wrapContentSize(),
                weaponDetails = weaponItem.listDetails
            )
        }
    }
}