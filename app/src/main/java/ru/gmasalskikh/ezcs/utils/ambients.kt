package ru.gmasalskikh.ezcs.utils

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import ru.gmasalskikh.ezcs.ui.theme.AppTheme

val LocalNavController = compositionLocalOf<NavHostController>{ error("No NavHostController found!") }
val LocalAppTheme = compositionLocalOf<AppTheme>{ error("No AppTheme found!") }
val LocalScaffoldState = compositionLocalOf<ScaffoldState>{ error("No ScaffoldState found!") }
