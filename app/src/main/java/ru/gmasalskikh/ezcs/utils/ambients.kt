package ru.gmasalskikh.ezcs.utils

import androidx.compose.runtime.ambientOf
import androidx.navigation.NavController
import ru.gmasalskikh.ezcs.screens.app_screen.AppState
import ru.gmasalskikh.ezcs.ui.theme.AppTheme

val AmbientNavController = ambientOf<NavController>()
val AmbientAppTheme = ambientOf<AppTheme>()
val AmbientAppState = ambientOf<AppState>()
