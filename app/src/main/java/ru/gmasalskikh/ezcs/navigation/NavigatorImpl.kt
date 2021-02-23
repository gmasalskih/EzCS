package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope

@Suppress("ObjectPropertyName")
class NavigatorImpl(
    private val cs: CustomCoroutineScope
) : Navigator {
    private val _navEvent: MutableSharedFlow<TargetNavigation> = MutableSharedFlow()
    override val navEventCollector: SharedFlow<TargetNavigation>
        get() = _navEvent.asSharedFlow()
    override val navEventEmitter: FlowCollector<TargetNavigation>
        get() = _navEvent

    override fun onAttach(navController: NavHostController) {
        cs.onStart()
        subscribeToNavEvent(navController)
    }

    override fun onDetach() {
        cs.onStop()
    }

    private fun subscribeToNavEvent(navController: NavHostController) = cs.launch {
        _navEvent.collect { navTarget ->
            when (navTarget) {
                TargetNavigation.Back -> navController.popBackStack()
                else -> {
                    navController.navigate(
                        navTarget.navId,
                        navTarget.params?.args,
                        navTarget.params?.navOptions,
                        navTarget.params?.navigatorExtras
                    )
                }
            }
        }
    }
}