package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

class PreviewViewModel : ViewModel() {
    var viewState by mutableStateOf(PreviewViewState())
        private set

    fun getCurrentIndexPage(): Int {
        return viewState.pagerState?.currentPage ?: 0
    }

    fun setPagerState(pagerState: PagerState) {
        viewState = viewState.copy(
            pagerState = pagerState
        )
    }

    fun getPagerState(): PagerState? {
        return viewState.pagerState
    }

//    @StringRes
//    fun getCurrentTopicRes(): Int = when (viewState.items[getCurrentIndexPage()].type) {
//        PreviewItemType.MAP_CALLOUTS -> R.string.map_callouts
//        PreviewItemType.COMPARE_WEAPONS -> R.string.compare_weapons
//        PreviewItemType.GRENADES_PRACTICE -> R.string.grenades_practice
//        PreviewItemType.WEAPON_CHARACTERISTICS -> R.string.weapon_characteristics
//        PreviewItemType.RANKS -> R.string.ranks
//    }


    fun navigateToMainMenu(navController: NavController) {
        navController.navigate(TargetNavigation.MainMenu.path) {
            popUpTo(TargetNavigation.Preview.path) { inclusive = true }
        }
    }

}