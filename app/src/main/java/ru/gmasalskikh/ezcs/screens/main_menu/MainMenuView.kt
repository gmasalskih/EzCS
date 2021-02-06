package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.screens.BaseView
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuItemContent
import ru.gmasalskikh.ezcs.ui.common_widget.MenuItem
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

class MainMenuView(
    override val vm: MainMenuViewModel
) : BaseView<MainMenuViewModel>() {


    @Composable
    override fun SetContent() {
        val theme = AmbientAppTheme.current
        val navController = AmbientNavController.current
        MainMenuContent(
            menuItemSurfaceColor = theme.colors.surface,
            menuItemElevation = theme.elevations.medium,
            menuItemShape = theme.shapes.medium,
            menuItemBorder = theme.borders.medium,
            onMenuItemClick = { mainMenuItemType ->
                vm.navigateTo(navController, mainMenuItemType)
            },
            items = vm.viewState.menuListItem
        )
    }
}

@Composable
fun MainMenuContent(
    menuItemSurfaceColor: Color,
    menuItemElevation: Dp,
    menuItemShape: CornerBasedShape,
    menuItemBorder: BorderStroke,
    onMenuItemClick: (MainMenuViewState.MainMenuItemType) -> Unit,
    items: List<MainMenuViewState.MainMenuItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items) { mainMenuItem ->
            MenuItem(
                backgroundColor = menuItemSurfaceColor,
                elevation = menuItemElevation,
                shape = menuItemShape,
                border = menuItemBorder,
                onClick = { onMenuItemClick(mainMenuItem.mainMenuItemType) }
            ) {
                MainMenuItemContent(
                    label = stringResource(mainMenuItem.menuItemNameRes),
                    backgroundRes = mainMenuItem.menuItemBackgroundImageRes
                )
            }
        }
    }
}