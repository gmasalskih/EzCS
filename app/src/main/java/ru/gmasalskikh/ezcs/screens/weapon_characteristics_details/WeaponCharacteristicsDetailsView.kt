package ru.gmasalskikh.ezcs.screens.weapon_characteristics_details

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics_details.widgets.WeaponCharacteristicsDetails


class WeaponCharacteristicsDetailsView(
    vm: WeaponCharacteristicsDetailsViewModel
) : BaseView<WeaponCharacteristicsDetailsViewState, Nothing, WeaponCharacteristicsDetailsViewModel>(vm)
{
    @Composable
    override fun SetContent(viewState: WeaponCharacteristicsDetailsViewState){
        viewState.weapon?.let { weapon ->
            WeaponCharacteristicsDetails(weapon)
        }
    }
}