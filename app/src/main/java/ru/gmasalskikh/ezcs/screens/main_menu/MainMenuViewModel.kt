package ru.gmasalskikh.ezcs.screens.main_menu

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainMenuViewModel : ViewModel() {
    var viewState: MainMenuViewState by mutableStateOf(MainMenuViewState())
        private set
}