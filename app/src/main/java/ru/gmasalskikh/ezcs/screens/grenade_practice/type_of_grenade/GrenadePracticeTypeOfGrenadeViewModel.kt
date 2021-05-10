package ru.gmasalskikh.ezcs.screens.grenade_practice.type_of_grenade

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
import ru.gmasalskikh.ezcs.data.app_entity.MapHolder
import ru.gmasalskikh.ezcs.data.firestore_entities.MapHolderFirestoreEntity
import ru.gmasalskikh.ezcs.data.firestore_entities.MapPointFirestoreEntity
import ru.gmasalskikh.ezcs.data.type.EntityType
import ru.gmasalskikh.ezcs.data.type.GrenadeType
import ru.gmasalskikh.ezcs.navigation.NavigationParams
import ru.gmasalskikh.ezcs.navigation.TargetNavigation
import ru.gmasalskikh.ezcs.navigation.TargetNavigationPath
import ru.gmasalskikh.ezcs.providers.app_controller.AppController
import ru.gmasalskikh.ezcs.providers.custom_coroutine_scope.CustomCoroutineScope
import ru.gmasalskikh.ezcs.providers.scope_manager.ScopeClosable
import ru.gmasalskikh.ezcs.providers.service_provider.ServiceProvider
import ru.gmasalskikh.ezcs.screens.BaseViewModel
import ru.gmasalskikh.ezcs.screens.SideEffect
import ru.gmasalskikh.ezcs.screens.grenade_practice.places_on_maps.PlacesOnMapsViewModel
import ru.gmasalskikh.ezcs.utils.toValidId

class GrenadePracticeTypeOfGrenadeViewModel(
    private val serviceProvider: ServiceProvider,
    private val appEventCollector: SharedFlow<AppController.AppEvent>,
    private val appEventEmitter: FlowCollector<AppController.AppEvent>,
    private val cs: CustomCoroutineScope
) : BaseViewModel<GrenadePracticeTypeOfGrenadeViewState, GrenadePracticeTypeOfGrenadeViewEvent>(),
    ScopeClosable {

    override val container: Container<GrenadePracticeTypeOfGrenadeViewState, SideEffect> =
        container(
            initialState = GrenadePracticeTypeOfGrenadeViewState(),
            onCreate = {
                cs.onStart()
                subscribeToAppEvent()
                initState()
            }
        )

    private fun initState() = cs.launch {
        serviceProvider.getEntityList(
            entityType = EntityType.MAP_POINT,
            clazz = MapPointFirestoreEntity::class.java,
            mapper = serviceProvider.mapper.mapPoint
        ).flatMap { mapPoint ->
            serviceProvider.getEntityList(
                entityType = EntityType.MAP_HOLDER,
                clazz = MapHolderFirestoreEntity::class.java,
                mapper = serviceProvider.mapper.mapHolder
            ).filter { mapHolder ->
                mapPoint.mapId == mapHolder.name.toValidId()
            }.apply {
                val mapHoldersMap: MutableMap<GrenadeType, List<MapHolder>> = mutableMapOf()
                mapHoldersMap[mapPoint.grenadeType] = this
                setMapHolderList(mapHoldersMap)
            }
        }
        setSideEffect(SideEffect.Data)
    }

    override suspend fun onViewEvent(viewEvent: GrenadePracticeTypeOfGrenadeViewEvent) {
        when (viewEvent) {
            is GrenadePracticeTypeOfGrenadeViewEvent.NavigateTo -> {
                val navParams = NavigationParams(
                    args = Bundle().apply {
                        putString(
                            PlacesOnMapsViewModel.GRENADE_PRACTICE_MAP_NAME,
                            viewEvent.mapHolder.name
                        )
                        putString(
                            PlacesOnMapsViewModel.GRENADE_PRACTICE_GRENADE_TYPE,
                            viewEvent.grenadeTypeName
                        )
                    }
                )
                appEventEmitter.emit(
                    AppController.AppEvent.NavigateTo(
                        TargetNavigation.TickRates(
                            params = navParams)
                    )
                )
            }
        }
    }

    private fun subscribeToAppEvent() = cs.launch {
        appEventCollector.collect {
            (it as? AppController.AppEvent.OnNavigateDestinationChanged)?.navEvent?.path?.let { path ->
                when (path) {
                    TargetNavigationPath.GRENADE_PRACTICE_SMOKE -> GrenadeType.SMOKE
                    TargetNavigationPath.GRENADE_PRACTICE_MOLOTOV -> GrenadeType.MOLOTOV
                    TargetNavigationPath.GRENADE_PRACTICE_FLASH -> GrenadeType.FLASH
                    else -> null
                }?.let { wt -> setCurrentGrenadeType(wt) }
            }
        }
    }

    private fun setCurrentGrenadeType(grenadeType: GrenadeType) = intent {
        reduce {
            state.copy(
                currentGrenadeType = grenadeType
            )
        }
    }

    private fun setMapHolderList(mapHolders: Map<GrenadeType, List<MapHolder>>) = intent {
        reduce {
            state.copy(
                mapHolders = mapHolders
            )
        }
    }

    override fun close() {
        cs.onStop()
        onCleared()
        Log.d("---", "GrenadesPracticeViewModel onCleared $this")
    }

    companion object {
        const val GRENADE_TYPE_NAME: String = "GRENADE_TYPE_NAME"
    }
}