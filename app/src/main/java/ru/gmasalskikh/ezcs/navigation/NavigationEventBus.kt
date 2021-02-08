package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

interface NavigationEventBus {
    val navigationEventBus: MutableStateFlow<TargetNavigation?>
    fun setNavController(navController: NavController)
}