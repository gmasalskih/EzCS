package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.ui.widget.MenuItem
import ru.gmasalskikh.ezcs.ui.widget.TopAppBar
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

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
        ScrollableColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuItem(
                backgroundColor = theme.colors.surface,
                elevation = theme.elevations.medium,
                shape = theme.shapes.medium,
                border = theme.borders.medium,
                onClick = { /*TODO*/ }) {
            }
        }
    }
}