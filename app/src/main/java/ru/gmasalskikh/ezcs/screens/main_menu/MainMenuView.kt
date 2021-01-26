package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.gmasalskikh.ezcs.ui.theme.darkGray
import ru.gmasalskikh.ezcs.ui.theme.orange
import ru.gmasalskikh.ezcs.ui.widget.TopAppBar
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@Composable
fun MainMenuView(
    navController: NavController = AmbientNavController.current,
//    vm: MainMenuViewModel = getViewModel { parametersOf(navController) }
) {
    TopAppBar(
        title = "",
        backgroundColor = orange,
        contentColor = darkGray,
        isNavigableBack = true
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainMenuView()
}