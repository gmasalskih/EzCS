package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientSavedStateRegistryOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.ui.theme.fontSize8Sp
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme

abstract class BaseView<VM : BaseViewModel<*>> {

    protected abstract val vm: VM

    @Composable
    protected abstract fun SetContent()

    @Composable
    fun Screen() {
        DisposableEffect(key1 = null) {
            onViewCreate()
            onDispose {
                onViewDestroy()
            }
        }
        val theme = AmbientAppTheme.current
        SetContent()
        when (val viewStateType = vm.viewStateType) {
            is ViewStateType.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black.copy(alpha = 0.6f))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center),
                        color = theme.colors.primary,
                    )
                }
            }
            is ViewStateType.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = theme.colors.error.copy(alpha = 0.8f)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = viewStateType.msgErr ?: "",
                        fontSize = fontSize8Sp,
                        color = theme.colors.onError
                    )
                    viewStateType.err?.printStackTrace()
                    Button(
                        onClick = vm::showDate,
                    ) {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            }
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