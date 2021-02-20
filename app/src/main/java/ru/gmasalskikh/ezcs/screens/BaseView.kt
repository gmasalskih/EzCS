package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.Flow
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.ui.common_widget.ErrorScreen
import ru.gmasalskikh.ezcs.ui.common_widget.LoadingIndicator
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.R

abstract class BaseView<VM : BaseViewModel<*>> {

    protected abstract val vm: VM

    @Composable
    protected abstract fun SetContent()

    @Composable
    fun Screen() {
        val viewSideEffect =
            vm.container.sideEffectFlow.collectAsState(initial = SideEffect.Data).value
        DisposableEffect(key1 = null) {
            onViewCreate()
            onDispose {
                onViewDestroy()
            }
        }
        val theme = AmbientAppTheme.current
        SetContent()
        when (viewSideEffect) {
            is SideEffect.Loading -> LoadingIndicator(color = theme.colors.primary)
            is SideEffect.Error -> {
                ErrorScreen(
                    message = viewSideEffect.msgErr ?: "",
                    backgroundColor = theme.colors.error.copy(alpha = 0.6f),
                    messageColor = theme.colors.onError,
                    confirmButtonLabel = stringResource(id = R.string.ok),
                    onConfirmButtonClick = {
                        //TODO Реализовать подтверждение при нажатии на кнопку onConfirmButtonClick
                    }
                )
            }
            else -> Unit
        }
    }

    protected open fun onViewCreate() {
        vm.onViewCreate()
        Log.d("---", "onViewCreate")
    }

    protected open fun onViewDestroy() {
        vm.onViewDestroy()
        Log.d("---", "onViewDestroy")
    }
}