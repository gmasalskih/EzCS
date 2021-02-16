package ru.gmasalskikh.ezcs.screens.preview

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

class PreviewViewModel(
    private val navEventEmitter: FlowCollector<TargetNavigation>
) : BaseViewModel<PreviewViewState>(
    defaultViewState = PreviewViewState(),
    initViewStateType = ViewStateType.Data
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

    fun navigateToMainMenu() = viewModelScope.launch {
        navEventEmitter.emit(
            TargetNavigation.MainMenu(
                params = NavigationParams(
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(TargetNavigation.Preview().navId, true)
                        .build()
                )
            )
        )
    }
}