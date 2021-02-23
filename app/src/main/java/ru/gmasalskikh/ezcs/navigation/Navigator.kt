package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val navEventEmitter: FlowCollector<TargetNavigation>
    val navEventCollector: SharedFlow<TargetNavigation>
    fun onAttach(navController: NavHostController)
    fun onDetach()
}