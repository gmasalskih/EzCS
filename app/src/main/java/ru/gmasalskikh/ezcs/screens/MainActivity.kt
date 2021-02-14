package ru.gmasalskikh.ezcs.screens

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import org.koin.core.component.KoinApiExtension
import ru.gmasalskikh.ezcs.screens.app_screen.AppStateHolderImpl
import ru.gmasalskikh.ezcs.screens.app_screen.AppView
import ru.gmasalskikh.ezcs.ui.theme.EzCSTheme

class MainActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
    }

    @KoinApiExtension
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
        setContent {
            EzCSTheme {
                AppView().Render()
            }
        }
    }
}
