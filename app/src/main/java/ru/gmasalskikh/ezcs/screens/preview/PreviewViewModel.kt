package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.*
import ru.gmasalskikh.ezcs.data.types.ScreenType
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

class PreviewViewModel : BaseViewModel<PreviewViewState>(
    defaultViewState = PreviewViewState(),
    screenType = ScreenType.FullScreen,
    viewStateType = ViewStateType.Data
) {


    fun getCurrentIndexPage(): Int {
        return viewState.pagerState?.currentPage ?: 0
    }

    @StringRes
    fun getCurrentTopic(): Int = viewState.items[getCurrentIndexPage()].topicRes


    fun setPagerState(pagerState: PagerState) {
        viewState = viewState.copy(
            pagerState = pagerState
        )
    }

    fun getPagerState(): PagerState? {
        return viewState.pagerState
    }

    fun navigateToMainMenu(navController: NavController) {
        navController.navigate(TargetNavigation.MainMenu.path) {
            popUpTo(TargetNavigation.Preview.path) { inclusive = true }
        }
    }


}