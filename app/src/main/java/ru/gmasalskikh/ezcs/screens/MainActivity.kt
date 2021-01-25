package ru.gmasalskikh.ezcs.screens

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.*
import ru.gmasalskikh.ezcs.navigation.ComposeNavigation
import ru.gmasalskikh.ezcs.ui.theme.EzCSTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        setContent {
            EzCSTheme {
                ComposeNavigation()
            }
        }
    }
}
