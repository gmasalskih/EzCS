package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.widgets.WeaponListItem
import ru.gmasalskikh.ezcs.ui.common_widget.ListContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class WeaponCharacteristicsView(
    vm: WeaponCharacteristicsViewModel,
) : BaseView<WeaponCharacteristicsViewState, WeaponCharacteristicsViewEvent, WeaponCharacteristicsViewModel>(
    vm
) {

    @Composable
    override fun SetContent(viewState: WeaponCharacteristicsViewState) {
        val theme = LocalAppTheme.current
        val weapons = viewState.weapons.filter { viewState.currentWeaponType == it.weaponType }
        ListContent(
            isScrollableList = true,
            listItemSurfaceColor = theme.colors.surface,
            listItemElevation = theme.elevations.medium,
            listItemShape = theme.shapes.medium,
            listItemBorder = theme.borders.thin,
            onListItemClick = { weapon ->
                emit(WeaponCharacteristicsViewEvent.NavigateTo(weapon = weapon))
            },
            items = weapons
        ) { weapon ->
            WeaponListItem(
                weapon = weapon,
                isSelected = viewState.selectedWeapon.contains(weapon)
            )
        }
    }
}