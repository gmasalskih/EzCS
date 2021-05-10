package ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps

import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import ru.gmasalskikh.ezcs.data.app_entity.MapPoint
import ru.gmasalskikh.ezcs.data.firestore_entities.MapPointFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.TickrateType
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeClosable
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.GrenadePracticeDetailsViewModel
import ru.gmasalskikh.ezcs.screens.grenade_practice.grenade_practice_details.GrenadePracticeDetailsViewState

class PlacesOnMapsViewModel(
//    private val mapName: String?,
    private val serviceProvider: ServiceProvider,
    private val appEventCollector: SharedFlow<AppController.AppEvent>,
    private val appEventEmitter: FlowCollector<AppController.AppEvent>,
    private val cs: CustomCoroutineScope
) : BaseViewModel<PlacesOnMapsViewState, PlacesOnMapsViewEvent>(), ScopeClosable {

    override val container: Container<PlacesOnMapsViewState, SideEffect> = container(
        initialState = PlacesOnMapsViewState(),
        onCreate = {
            cs.onStart()
            initState()
            subscribeToAppEvent()
        }
    )

    private fun initState() = cs.launch {
        serviceProvider.getEntityList(
            entityType = EntityType.MAP_POINT,
            clazz = MapPointFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.mapPoint
        )/*.filter { mapPoint ->
            mapPoint.mapId == mapName?.toLowerCase(Locale.getDefault())
        }*/.apply {
            setMapPointList(this)
        }
        setSideEffect(SideEffect.Data)
    }

    private fun setMapPointList(mapPoints: List<MapPoint>) = intent {
        reduce {
            state.copy(items = mapPoints)
        }
    }

    private fun subscribeToAppEvent() = cs.launch {
        appEventCollector.collect {
            (it as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent?.path?.let { path ->
                when (path) {
                    TargetNavigationPath.GRENADE_PRACTICE_TICKRATE_64 -> TickrateType.TICKRATE_64
                    TargetNavigationPath.GRENADE_PRACTICE_TICKRATE_128 -> TickrateType.TICKRATE_128
                    else -> null
                }?.let { tr -> setCurrentTickrateType(tr) }
            }
        }
    }

    private fun setCurrentTickrateType(tickrateType: TickrateType) = intent {
        reduce {
            state.copy(
                currentTickrateType = tickrateType
            )
        }
    }

    override suspend fun onViewEvent(viewEvent: PlacesOnMapsViewEvent) {
        when (viewEvent) {
            is PlacesOnMapsViewEvent.NavigateTo -> {
                val navParams = NavigationParams(
                    args = Bundle().apply {
                        putString(
                            GrenadePracticeDetailsViewModel.GRENADE_PRACTICE_DETAILS_MAPPOINT_FAIR_NAME,
                            viewEvent.mapPoint.fairName
                        )
                        putString(
                            GrenadePracticeDetailsViewModel.GRENADE_PRACTICE_DETAILS_MAPPOINT_NAME,
                            viewEvent.mapPoint.name
                        )
                    }
                )
                appEventEmitter.emit(
                    AppController.AppEvent.NavigateTo(
                        TargetNavigation.GrenadePracticeDetails(
                            params = navParams
                        )
                    )
                )
            }
        }
    }

    override fun close() {
        cs.onStop()
        onCleared()
        Log.d("---", "PlacesOnMapsViewModel onCleared $this")
    }

    companion object {
        const val GRENADE_PRACTICE_MAP_NAME = "GRENADE_PRACTICE_MAP_NAME"
        const val GRENADE_PRACTICE_GRENADE_TYPE = "GRENADE_PRACTICE_GRENADE_TYPE"
    }

}