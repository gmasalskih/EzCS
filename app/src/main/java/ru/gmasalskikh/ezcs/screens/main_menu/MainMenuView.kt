package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuContent
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

class MainMenuView(
    override val vm: MainMenuViewModel
) : BaseView<MainMenuViewModel>() {

    @Composable
    override fun SetContent() {
        val theme = AmbientAppTheme.current
        MainMenuContent(
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.medium,
            onMenuItemClick = { mainMenuItemType ->
                vm.navigateTo(mainMenuItemType)
            },
            items = vm.viewState.menuListItem
        )
    }
}