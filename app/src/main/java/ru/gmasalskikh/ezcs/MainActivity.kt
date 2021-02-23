package ru.gmasalskikh.ezcs

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.gmasalskikh.ezcs.screens.app_screen.AppView
import ru.gmasalskikh.ezcs.ui.theme.EzCSTheme
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolder.*
import ru.gmasalskikh.ezcs.providers.lifecycle_holder.LifecycleHolder.LifecycleActivityEvent.*
import ru.gmasalskikh.ezcs.di.NamesOfDependencies.*

class MainActivity : AppCompatActivity() {

    private val lifecycleEmitter by inject<FlowCollector<LifecycleActivityEvent>>(
        named(LIFECYCLE_EMITTER)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch { lifecycleEmitter.emit(ON_CREATE) }
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
        setContent {
            EzCSTheme {
                AppView()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_START) }
    }

    override fun onRestart() {
        super.onRestart()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_RESTART) }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_RESUME) }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_PAUSE) }
    }

    override fun onStop() {
        super.onStop()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_STOP) }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch { lifecycleEmitter.emit(ON_DESTROY) }
    }
}