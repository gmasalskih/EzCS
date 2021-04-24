package ru.gmasalskikh.ezcs.screens.weapon_characteristics

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.weapon_characteristics.widgets.WeaponListItem
import ru.gmasalskikh.ezcs.ui.common_widget.MenuContent
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
        MenuContent(
            isScrollableMenu = true,
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.thin,
            onMenuItemClick = { weapon ->
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