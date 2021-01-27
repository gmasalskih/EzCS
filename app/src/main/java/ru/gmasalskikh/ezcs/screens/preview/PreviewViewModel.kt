package ru.gmasalskikh.ezcs.screens.preview

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

class PreviewViewModel : ViewModel() {
    var viewState by mutableStateOf(PreviewViewState())
        private set

    fun getCurrentIndexPage(): Int {
        return viewState.pagerState?.currentPage ?: 0
    }

    fun getCurrentItem(): String {
        return viewState.items[getCurrentIndexPage()]
    }

    fun setPagerState(pagerState: PagerState) {
        viewState = viewState.copy(
            pagerState = pagerState
        )
    }

    fun getPagerState(): PagerState? {
        return viewState.pagerState
    }

    fun navigateToMainMenu(navController: NavController) {
        navController.navigate(TargetNavigation.MAIN_MENU.name) {
            popUpTo(TargetNavigation.PREVIEW.name) { inclusive = true }
        }
    }

}