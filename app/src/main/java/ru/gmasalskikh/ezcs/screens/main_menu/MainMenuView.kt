package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.model.main_menu.MainMenuItemType
import ru.gmasalskikh.ezcs.screens.main_menu.widget.MainMenuItemContent
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.ui.common_widget.MenuItem
import ru.gmasalskikh.ezcs.ui.common_widget.TopAppBar
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

private fun getPairRes(mainMenuItemType: MainMenuItemType): Pair<Int, Int> =
    when (mainMenuItemType) {
        MainMenuItemType.MAP_CALLOUTS -> {
            R.string.map_callouts to R.drawable.main_menu_map_callouts
        }
        MainMenuItemType.GRENADES_PRACTICE -> {
            R.string.grenades_practice to R.drawable.main_menu_grenades_practice
        }
        MainMenuItemType.WEAPON_CHARACTERISTICS -> {
            R.string.weapon_characteristics to R.drawable.main_menu_weapon_characteristics
        }
        MainMenuItemType.RANKS -> {
            R.string.ranks to R.drawable.main_menu_ranks
        }
    }

@StringRes
private fun getMainMenuItemLabelRes(mainMenuItemType: MainMenuItemType): Int =
    getPairRes(mainMenuItemType = mainMenuItemType).first

@DrawableRes
private fun getMainMenuItemBackgroundRes(mainMenuItemType: MainMenuItemType): Int =
    getPairRes(mainMenuItemType = mainMenuItemType).second

@Composable
fun MainMenuView(
    navController: NavController = AmbientNavController.current,
    theme: AppTheme = AmbientAppTheme.current,
    vm: MainMenuViewModel = getViewModel()
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
                    onClick = { /*TODO*/ }
                ) {
                    MainMenuItemContent(
                        label = stringResource(id = getMainMenuItemLabelRes(mainMenuItem.mainMenuItemType)),
                        backgroundRes = getMainMenuItemBackgroundRes(mainMenuItem.mainMenuItemType)
                    )
                }
            }
        }
    }
}