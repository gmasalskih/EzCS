package ru.gmasalskikh.ezcs.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.R
import ru.gmasalskikh.ezcs.data.types.ScreenType
import ru.gmasalskikh.ezcs.data.types.ViewStateType
import ru.gmasalskikh.ezcs.ui.common_widget.TopAppBar
import ru.gmasalskikh.ezcs.ui.theme.fontSize8Sp
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@KoinApiExtension
abstract class BaseView<VM : BaseViewModel<*>> {

    protected abstract val vm: VM

    @Composable
    protected abstract fun SetContent()

    @Composable
    fun Screen() {
        val theme = AmbientAppTheme.current
        val navController = AmbientNavController.current
        DisposableEffect(key1 = Unit) {
            onViewCreate(navController)
            onDispose {
                onViewDestroy()
            }
        }
        AmbientLifecycleOwner.current.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onLifecycleListener(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> onActivityCreate()
                    Lifecycle.Event.ON_START -> onActivityStart()
                    Lifecycle.Event.ON_RESUME -> onActivityResume()
                    Lifecycle.Event.ON_PAUSE -> onActivityPause()
                    Lifecycle.Event.ON_STOP -> onActivityStop()
                    Lifecycle.Event.ON_DESTROY -> {
                        lifecycleOwner.lifecycle.removeObserver(this)
                    }
                    else -> Unit
                }
            }
        })
        when (val screenType = vm.screenState.screenType) {
            is ScreenType.FullScreen -> RenderViewStateType()
            is ScreenType.WithAppBar -> {
                TopAppBar(
                    title = screenType.appBarTitle,
                    backgroundColor = theme.colors.primary,
                    contentColor = theme.colors.onPrimary,
                    elevation = theme.elevations.medium,
                    additionActionContent = screenType.additionActionContent
                ) {
                    RenderViewStateType()
                }
            }
        }
    }

    @Composable
    private fun RenderViewStateType() {
        val theme = AmbientAppTheme.current
        SetContent()
        when (val viewStateType = vm.screenState.viewStateType) {
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

    protected open fun onViewCreate(navController: NavController) {
        vm.onViewCreate(navController)
        Log.d("---", "onViewCreate")
    }

    protected open fun onActivityCreate() {
        Log.d("---", "onActivityCreate")
    }

    protected open fun onActivityStart() {
        Log.d("---", "onActivityStart")
    }

    protected open fun onActivityResume() {
        Log.d("---", "onActivityResume")
    }

    protected open fun onActivityPause() {
        Log.d("---", "onActivityPause")
    }

    protected open fun onActivityStop() {
        Log.d("---", "onActivityStop")
    }

    protected open fun onActivityDestroy() {
        Log.d("---", "onActivityDestroy")
    }

    protected open fun onViewDestroy() {
        vm.onViewDestroy()
        Log.d("---", "onViewDestroy")
    }
}