package ru.gmasalskikh.ezcs.screens.preview

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.providers.lifecycle_keeper.LifecycleKeeper
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.preview.widgets.PagerState

class PreviewViewModel(
    private val lifecycle: SharedFlow<LifecycleKeeper.LifecycleActivityEvent>
) : BaseViewModel<PreviewViewState>(
    defaultViewState = PreviewViewState(),
    initViewStateType = ViewStateType.Data
) {

    init {
        viewModelScope.launch {
            lifecycle.collect { event ->
                Log.d("---", event.name)
            }
        }
    }

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
        navController.navigate(TargetNavigation.MainMenu().path) {
            popUpTo(TargetNavigation.Preview().path) { inclusive = true }
        }
    }


}