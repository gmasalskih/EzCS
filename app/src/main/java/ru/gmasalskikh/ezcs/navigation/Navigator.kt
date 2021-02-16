package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.FlowCollector

interface Navigator {
    val navEventEmitter: FlowCollector<TargetNavigation>
    fun onAttach(navController: NavHostController)
    fun onDetach()
}