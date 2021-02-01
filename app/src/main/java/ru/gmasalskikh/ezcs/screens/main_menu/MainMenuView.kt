package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.providers.mapper.ResourceMapper
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuItemContent
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.ui.common_widget.MenuItem
import ru.gmasalskikh.ezcs.ui.common_widget.TopAppBar
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun MainMenuView(
    navController: NavController = AmbientNavController.current,
    theme: AppTheme = AmbientAppTheme.current,
    vm: MainMenuViewModel = getViewModel(),
    resourceMapper: ResourceMapper = get()
) {
    TopAppBar(
        title = stringResource(R.string.menu),
        backgroundColor = theme.colors.primary,
        contentColor = theme.colors.onPrimary,
        elevation = theme.elevations.medium,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(vm.viewState.menuListItem) { mainMenuItem ->
                MenuItem(
                    backgroundColor = theme.colors.surface,
                    elevation = theme.elevations.medium,
                    shape = theme.shapes.medium,
                    border = theme.borders.medium,
                    onClick = { vm.navigateTo(navController, mainMenuItem.mainMenuItemType) }
                ) {
                    MainMenuItemContent(
                        label = stringResource(resourceMapper.getStringRes(mainMenuItem.mainMenuItemType)),
                        backgroundRes = resourceMapper.getDrawableRes(mainMenuItem.mainMenuItemType)
                    )
                }
            }
        }
    }
}