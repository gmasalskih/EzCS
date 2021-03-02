package ru.gmasalskikh.ezcs.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import ru.gmasalskikh.ezcs.ui.common_widget.ErrorScreen
import ru.gmasalskikh.ezcs.ui.common_widget.LoadingIndicator
import ru.gmasalskikh.ezcs.utils.LocalAppTheme
import ru.gmasalskikh.ezcs.R

abstract class BaseView<VS : ViewState, VE : ViewEvent, VM : BaseViewModel<VS, VE>>(
    private val vm: VM
) {

    private val viewEventEmitter: FlowCollector<VE> = vm.viewEventEmitter
    private val sideEffectFlow: Flow<SideEffect> = vm.container.sideEffectFlow
    private val viewStateFlow: Flow<VS> = vm.container.stateFlow
    private val currentViewState: VS = vm.container.currentState
    private lateinit var cs: CoroutineScope

    protected fun emit(viewEvent: VE) {
        cs.launch {
            viewEventEmitter.emit(viewEvent)
        }
    }

    @Composable
    protected abstract fun SetContent(viewState: VS)

    @Composable
    fun Screen() {
        val viewState = viewStateFlow.collectAsState(initial = currentViewState).value
        val viewSideEffect =
            sideEffectFlow.collectAsState(initial = viewState.currentSideEffect).value
        cs = rememberCoroutineScope()
        DisposableEffect(key1 = null) {
            onViewCreate()
            onDispose {
                onViewDestroy()
            }
        }
        val theme = LocalAppTheme.current
        SetContent(viewState)
        when (viewSideEffect) {
            is SideEffect.Loading -> {
                LoadingIndicator(color = theme.colors.primary)
            }
            is SideEffect.Error -> {
                ErrorScreen(
                    message = viewSideEffect.msgErr ?: "",
                    backgroundColor = theme.colors.error.copy(alpha = 0.6f),
                    messageColor = theme.colors.onError,
                    confirmButtonLabel = stringResource(id = R.string.ok),
                    onConfirmButtonClick = {
                        vm.intent {
                            postSideEffect(SideEffect.Data)
                        }
                    }
                )
            }
            SideEffect.Data -> Unit
        }
    }

    private fun onViewCreate() {
        vm.onViewCreate()
    }

    private fun onViewDestroy() {
        vm.onViewDestroy()
    }
}