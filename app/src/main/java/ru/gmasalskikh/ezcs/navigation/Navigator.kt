package ru.gmasalskikh.ezcs.navigation

import android.os.Bundle
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val targetNavigationEmitter: FlowCollector<TargetNavigation>
    val navEventFlow: Flow<NavEvent>
    fun onAttach(navController: NavHostController)
    fun onDetach()

    data class NavEvent(
        val path: TargetNavigationPath,
        val bundle: Bundle?
    )
}