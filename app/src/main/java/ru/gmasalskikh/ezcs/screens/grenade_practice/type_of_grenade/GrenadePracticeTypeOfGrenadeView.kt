package ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade.widgets.GrenadesPracticeMenuItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.MenuContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class GrenadePracticeTypeOfGrenadeView(
    vm: GrenadePracticeTypeOfGrenadeViewModel
) : BaseView<GrenadePracticeTypeOfGrenadeViewState,
        GrenadePracticeTypeOfGrenadeViewEvent,
        GrenadePracticeTypeOfGrenadeViewModel>(vm) {

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    override fun SetContent(viewState: GrenadePracticeTypeOfGrenadeViewState) {
        val theme = LocalAppTheme.current
        MenuContent(
            isScrollableMenu = true,
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.medium,
            onMenuItemClick = { mapHolder ->
                emit(GrenadePracticeTypeOfGrenadeViewEvent.NavigateTo(mapHolder = mapHolder)) },
            items = viewState.mapHolders.getOrDefault(viewState.currentGrenadeType, listOf())
        ) { mapHolder ->
            GrenadesPracticeMenuItemContent(mapHolder = mapHolder)
        }
    }
}