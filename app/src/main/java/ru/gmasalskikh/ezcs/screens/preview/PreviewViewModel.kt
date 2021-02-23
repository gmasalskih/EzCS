package ru.gmasalskikh.ezcs.screens.preview

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect

class PreviewViewModel(
    private val navEventEmitter: FlowCollector<TargetNavigation>,
) : BaseViewModel<PreviewViewState, PreviewViewEvent>() {

    override val container: Container<PreviewViewState, SideEffect> = container(
        initialState = PreviewViewState()
    )

    override suspend fun onViewEvent(viewEvent: PreviewViewEvent) {
        when (viewEvent) {
            is PreviewViewEvent.NavigateNext -> viewModelScope.launch(viewLifecycleJob) {
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
            is PreviewViewEvent.SetPagerState -> intent {
                reduce {
                    state.copy(pagerState = viewEvent.pagerState)
                }
            }
        }
    }
}