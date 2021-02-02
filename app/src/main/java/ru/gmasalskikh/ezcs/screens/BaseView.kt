package ru.gmasalskikh.ezcs.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.AmbientLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.NavController
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gmasalskikh.ezcs.providers.coroutines.ViewCoroutineScope
import ru.gmasalskikh.ezcs.ui.theme.AppTheme
import ru.gmasalskikh.ezcs.utils.AmbientAppTheme
import ru.gmasalskikh.ezcs.utils.AmbientNavController

@KoinApiExtension
abstract class BaseView<VM : BaseViewModel<*>> : KoinComponent {
    protected abstract val vm: VM
    protected val cs: ViewCoroutineScope by inject()
    protected lateinit var theme: AppTheme
        private set
    protected lateinit var navController: NavController
        private set
    private lateinit var lifecycleOwner: LifecycleOwner
    private var lifecycleObserver: LifecycleObserver? = null

    @Composable
    fun Screen() {
        LaunchedEffect(key1 = Unit) { onViewCreate() }
        DisposableEffect(key1 = Unit) { onDispose(::onViewDestroy) }
        theme = AmbientAppTheme.current
        navController = AmbientNavController.current
        lifecycleOwner = AmbientLifecycleOwner.current
        lifecycleObserver = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onLifecycleListener(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> onActivityCreate()
                    Lifecycle.Event.ON_START -> onActivityStart()
                    Lifecycle.Event.ON_RESUME -> onActivityResume()
                    Lifecycle.Event.ON_PAUSE -> onActivityPause()
                    Lifecycle.Event.ON_STOP -> onActivityStop()
                    Lifecycle.Event.ON_DESTROY -> onActivityDestroy()
                    else -> Unit
                }
            }
        }.also { observer -> lifecycleOwner.lifecycle.addObserver(observer) }
        setContent()
    }

    @SuppressLint("ComposableNaming")
    @Composable
    protected abstract fun setContent()

    protected open fun onViewCreate() {
        vm.onViewCreate()
    }

    protected open fun onViewDestroy() {
        lifecycleObserver?.let { observer -> lifecycleOwner.lifecycle.removeObserver(observer) }
        lifecycleObserver = null
        vm.onViewDestroy()
    }

    protected open fun onActivityCreate() {
        Log.d("---", "onActivityCreate")
    }

    protected open fun onActivityStart() {
        Log.d("---", "onActivityStart")
    }

    protected open fun onActivityResume() {
        cs.onStart()
        Log.d("---", "onActivityResume")
    }

    protected open fun onActivityPause() {
        Log.d("---", "onActivityPause")
    }

    protected open fun onActivityStop() {
        cs.onStop()
        Log.d("---", "onActivityStop")
    }

    protected open fun onActivityDestroy() {
        Log.d("---", "onActivityDestroy")
    }
}