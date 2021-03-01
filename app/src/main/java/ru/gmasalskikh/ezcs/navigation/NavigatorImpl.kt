package ru.gmasalskikh.ezcs.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope


@Suppress("ObjectPropertyName")
class NavigatorImpl(
    private val cs: CustomCoroutineScope,
) : Navigator {
    private val _targetNavigationSharedFlow: MutableSharedFlow<TargetNavigation> =
        MutableSharedFlow()
    override val targetNavigationEmitter: FlowCollector<TargetNavigation>
        get() = _targetNavigationSharedFlow

    private val _navEvent: MutableSharedFlow<Navigator.NavEvent?> = MutableSharedFlow()
    override val navEventFlow: Flow<Navigator.NavEvent>
        get() = _navEvent.runningReduce { accumulator, value ->
            if (accumulator?.path == value?.path) null else value
        }.filterNotNull()

    private lateinit var navController: NavHostController

    private fun emitNavEvent(navEvent: Navigator.NavEvent) {
        cs.launch {
            _navEvent.emit(navEvent)
        }

    }

    private val destinationChangedListener =
        NavController.OnDestinationChangedListener { _, _, arguments ->
            arguments?.getString(KEY_ROUTE)?.let { rout ->
                emitNavEvent(
                    Navigator.NavEvent(
                        TargetNavigationPath.valueOf(rout),
                        arguments
                    )
                )
            }
        }

    override fun onAttach(navController: NavHostController) {
        cs.onStart()
        this.navController = navController.apply {
            addOnDestinationChangedListener(destinationChangedListener)
        }
        subscribeToTargetNavigationEvent()

    }

    override fun onDetach() {
        navController.removeOnDestinationChangedListener(destinationChangedListener)
        cs.onStop()
    }

    private fun subscribeToTargetNavigationEvent() = cs.launch {
        _targetNavigationSharedFlow.collect { navTarget ->
            when (navTarget) {
                TargetNavigation.Back -> navController.popBackStack()
                else -> {
                    emitNavEvent(
                        Navigator.NavEvent(navTarget.targetNavigationPath, navTarget.params?.args)
                    )
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