package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.Composable
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.ListContent
import ru.gmasalskikh.ezcs.utils.LocalAppTheme

class MainMenuView(
    vm: MainMenuViewModel
) : BaseView<MainMenuViewState, MainMenuViewEvent, MainMenuViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MainMenuViewState) {
        val theme = LocalAppTheme.current
        ListContent(
            listItemSurfaceColor = theme.colors.surface,
            listItemElevation = theme.elevations.medium,
            listItemShape = theme.shapes.medium,
            listItemBorder = theme.borders.medium,
            onListItemClick = { mainMenuItemType ->
                emit(MainMenuViewEvent.NavigateTo(mainMenuItemType.mainMenuItemType))
            },
            items = viewState.menuListItem
        ) { mainMenuItem ->
            MainMenuItemContent(
                labelRes = mainMenuItem.menuItemNameRes,
                backgroundRes = mainMenuItem.menuItemBackgroundImageRes
            )
        }
    }
}
