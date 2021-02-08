package ru.gmasalskikh.ezcs.utils

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.ambientOf
import androidx.navigation.NavHostController
import ru.gmasalskikh.ezcs.ui.theme.AppTheme

val AmbientNavController = ambientOf<NavHostController>()
val AmbientAppTheme = ambientOf<AppTheme>()
val AmbientScaffoldState = ambientOf<ScaffoldState>()
