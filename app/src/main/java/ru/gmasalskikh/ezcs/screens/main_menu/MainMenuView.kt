package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuContent
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

class MainMenuView(
    vm: MainMenuViewModel
) : BaseView<MainMenuViewEvent, MainMenuViewState, MainMenuViewModel>(vm) {

    @Composable
    override fun SetContent(viewState: MainMenuViewState) {
        val cs = rememberCoroutineScope()
        val theme = AmbientAppTheme.current
        MainMenuContent(
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.medium,
            onMenuItemClick = { mainMenuItemType ->
                cs.launch {
                    emit(MainMenuViewEvent.NavigateTo(mainMenuItemType))
                }
            },
            items = viewState.menuListItem
        )
    }
}